<template>
  <div>
    <div class="title">
      <h2>校内车辆停车管理</h2>
      <Input style="width:200px" search placeholder="搜索车牌号" />
    </div>
    <car-live-image></car-live-image>
  </div>
</template>
<script>
import CarLiveImage from './carLiveImage'
export default {
  components: {
    CarLiveImage
  },
  data() {
    return {}
  }
}
</script>
<style scoped>
.title {
  display: flex;
  justify-content: space-between;
  margin-bottom: 11px;
}
</style>
