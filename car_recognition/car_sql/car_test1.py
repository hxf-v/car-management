# coding:utf-8
import os
import shutil
import pandas as pd

from sqlalchemy import create_engine

from car_spot import recognize_plate

# 连接配置
db_url = "mssql+pymssql://sa:zhude@123@10.253.0.202:1433/CarManagement"
engine = create_engine(db_url, encoding='utf8', echo=False)

# 待操作的文件夹路径
file_dir = r"F:\savepic"
fileNames = os.walk(file_dir)  # 当前文件夹下的所有文件名
# 实例化一个空DataFrame 用于后续append， columns为列名，需和数据库对应的表字段名一致
res_ls = pd.DataFrame(columns=('car_id', 'confidence', 'pic_path',  'state', 'take_time', 'car_color', 'car_location'))

path1 = r"F:\used_picture"

for path, dir_list, file_list in fileNames:
    for row in file_list:
        if '.jpg' in row:
            file = path + '\\' + row
            used_file = path1 + '\\' + row
            res = recognize_plate(pict=file, take_pic_time=row.split('-')[1], used_file=used_file, car_location=row.split('-')[0])

            if res is None:
                os.remove(file)
            else:
                res_ls = res_ls.append(res, ignore_index=True)
                shutil.move(file, path1)


# 识别结果集合的DataFrame不为空则存储到数据库，对应表为CarNumberGet
if res_ls.empty is False:
    res_ls.to_sql('CarNumberGet', con=engine, if_exists='append', index=False)
