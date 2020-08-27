<template>
  <div>
    <div class="schoolCarDate">
      <div class="carDateItem green">
        <div class="itemNum">160</div>
        <div class="itemText">校内车辆</div>
      </div>
      <div class="carDateItem red">
        <div class="itemNum">36</div>
        <div class="itemText">违规车辆</div>
      </div>
      <div class="carDateItem juice">
        <div class="itemNum">49</div>
        <div class="itemText">未跟踪车辆</div>
      </div>
      <div class="carDateItem blue">
        <div class="itemNum">1260</div>
        <div class="itemText">注册车辆</div>
      </div>
    </div>
    <h2 style="margin:20px 0 11px 0">校内车辆轨迹信息</h2>
    <Amap></Amap>
  </div>
</template>
<script>
import Amap from './Amap.vue'
export default {
  components: {
    Amap
  }
}
</script>
<style scoped>
.schoolCarDate {
  display: flex;
  width: 100%;
  height: 120px;
  margin-top: 11px;
  justify-content: space-between;
}
.carDateItem {
  height: 120px;
  flex: 0 0 24%;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  align-items: center;
  color: white;
  cursor: pointer;
  opacity: 0.8;
}
.carDateItem:hover {
  opacity: 1;
}
.itemNum {
  font-weight: bold;
  font-size: 36px;
  flex: 0 0 70px;
  line-height: 70px;
}
.itemText {
  font-size: 20px;
  flex: 1;
}
.green {
  background: rgb(88, 202, 154);
}
.blue {
  background: rgb(68, 126, 255);
}
.red {
  background: rgb(238, 112, 109);
}
.juice {
  background: rgb(247, 218, 71);
}
</style>
