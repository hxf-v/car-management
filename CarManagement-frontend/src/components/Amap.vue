<template>
  <div class="amap-page-container">
    <el-amap vid="amapDemo" :center="center" :map-manager="amapManager" :zoom="zoom" :events="events" class="amap-demo">
    </el-amap>
  </div>
</template>
<script>
import { AMapManager } from 'vue-amap'

export default {
  name: 'Amap',
  data() {
    return {
      zoom: 11,
      center: [117.184006, 31.766728],
      amapManager: '',
      events: null
    }
  },
  methods: {
    initMaps() {
      let path = [
        {
          name: '路线0',
          path: [
            [117.187824, 31.766857],
            [117.186429, 31.767532],
            [117.186182, 31.769275],
            [117.186536, 31.772485],
            [117.187652, 31.774155],
            [117.187534, 31.777037]
          ]
        },
        {
          name: '路线1',
          path: [
            [117.179831, 31.767852],
            [117.18012, 31.766807],
            [117.181349, 31.765704],
            [117.183779, 31.764135],
            [117.187035, 31.763378],
            [117.189331, 31.761813]
          ]
        }
      ]
      this.events = {
        init(map) {
          AMapUI.load(['ui/misc/PathSimplifier', 'lib/$'], function(
            PathSimplifier,
            $
          ) {
            if (!PathSimplifier.supportCanvas) {
              alert('当前环境不支持 Canvas！')
              return
            }
            var pathSimplifierIns = new PathSimplifier({
              zIndex: 100,
              //autoSetFitView:false,
              map: map, //所属的地图实例

              getPath: function(pathData, pathIndex) {
                return pathData.path
              },

              getHoverTitle: function(pathData, pathIndex, pointIndex) {
                if (pointIndex >= 0) {
                  //point
                  return (
                    pathData.name +
                    '，点：' +
                    pointIndex +
                    '/' +
                    pathData.path.length
                  )
                }
                return pathData.name + '，经过摄像头数量' + pathData.path.length
              },

              renderOptions: {
                renderAllPointsIfNumberBelow: 100 //绘制路线节点，如不需要可设置为-1
              }
            })

            window.pathSimplifierIns = pathSimplifierIns

            //设置数据
            pathSimplifierIns.setData(path)

            //创建一个巡航器
            for (let index = 0; index < path.length; index++) {
              pathSimplifierIns
                .createPathNavigator(index, {
                  loop: true, //循环播放
                  speed: 150 //巡航速度，单位千米/小时
                })
                .start()
            }
          })
        }
      }
    }
  },
  mounted() {
    this.initMaps()
  }
}
</script>

<style scoped>
.amap-page-container {
  width: 100%;
  height: 600px;
}
</style>
