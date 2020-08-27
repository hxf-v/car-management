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
pre_pic = os.listdir(file_dir)  # 当前文件夹下的所有文件名
# 实例化一个空DataFrame 用于后续append， columns为列名，需和数据库对应的表字段名一致
res_ls = pd.DataFrame(columns=('car_id', 'confidence', 'pic_path',  'state', 'take_time', 'car_color'))
path1 = r"F:\used_picture"

# 遍历文件夹下的文件，若为jpg格式则识别，结果为none的删除，反之 移动到file_dir路径的used_picture文件夹下
for row in pre_pic:
    if '.jpg' in row:
        file = file_dir + '\\' + row
        used_file = file_dir + '\\' + row
        res = recognize_plate(pict=file, take_pic_time=row.split('-')[1], used_file=used_file)
        if res is None:
            os.remove(file)
        else:
            res_ls = res_ls.append(res, ignore_index=True)
            shutil.move(file, path1)


# 识别结果集合的DataFrame不为空则存储到数据库，对应表为CarNumberGet
if res_ls.empty is False:
    res_ls.to_sql('CarNumberGet', con=engine, if_exists='append', index=False)

