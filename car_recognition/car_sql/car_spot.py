import sys
import cv2

import numpy as np
import HyperLPRLite as pr
# from PIL import Image
# from PIL import ImageDraw

from PIL import ImageFont
from datetime import datetime
from pandas import DataFrame


import imp
imp.reload(sys)
fontC = ImageFont.truetype("Font/platech.ttf", 14, 0)

'''
# 从本地读取图片并做识别，返回所有识别到车牌的【返回车牌号，置信度， 文件路径， 默认的待处理状态， 拍照时间】
# smallest_confidence：最小置信度
'''


def recognize_plate(pict, take_pic_time, used_file, car_location, state='待处理', smallest_confidence=0.7, car_color="黑色"):
    image = cv2.imdecode(np.fromfile(pict, dtype=np.uint8), -1)
    model = pr.LPR("model/cascade.xml", "model/model12.h5", "model/ocr_plate_all_gru.h5")  # 训练好的模型参数
    res_model = model.SimpleRecognizePlateByE2E(image)
    return_all_plate = []
    if res_model.__len__() == 0:  # 若未识别出车牌，直接返回
        return
    take_pic_time = datetime.strptime(take_pic_time, '%Y%m%d%H%M%S')  # 字符串转datetime类型
    for pstr, confidence, rect in res_model:
        confidence = round(confidence, 2)
        if confidence > smallest_confidence:
            # 返回车牌号，置信度， 文件路径， 默认的待处理状态， 拍照时间
            return_all_plate.append([pstr, confidence, used_file,  state, take_pic_time, car_color, car_location])
    #  list转DataFrame用于后续存数据库
    df = DataFrame(return_all_plate, columns=['car_id', 'confidence', 'pic_path',  'state', 'take_time', 'car_color', 'car_location'])
    return df

