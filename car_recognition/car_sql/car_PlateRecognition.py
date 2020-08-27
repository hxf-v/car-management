#coding=utf-8
from PIL.ImageFont import ImageFont
from gevent.libev.corecext import sys

import HyperLPRLite as pr
import cv2
import numpy as np
import imp
imp.reload(sys)
fontC = ImageFont.truetype("Font/platech.ttf", 14, 0)

# 从本地读取图片并做识别，返回所有识别到车牌的【识别结果，置信度，位置】
# smallest_confidence：最小置信度
def recognize_plate(image, smallest_confidence = 0.7):
    model = pr.LPR("model/cascade.xml", "model/model12.h5", "model/ocr_plate_all_gru.h5") #训练好的模型参数
    model.SimpleRecognizePlateByE2E(image)
    return_all_plate = []
    for pstr,confidence,rect in model.SimpleRecognizePlateByE2E(image):
        if confidence>smallest_confidence:
            return_all_plate.append([pstr,confidence,rect])  #返回车牌号，车牌位置，置信度
    return return_all_plate

test_image = cv2.imread("Images/16.jpg") #读入图片
print(recognize_plate(test_image)) #打印结果，车牌号，车牌位置，置信度