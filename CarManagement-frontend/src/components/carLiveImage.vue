<template>
  <div class="liveContent">
    <div class="block" v-for="fit in fits" :key="fit">
      <span class="demonstration">{{ fit }}</span>
      <el-image style="width: 200px; height: 200px" :src="url" :fit="fit"></el-image>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      fits: ['contain', 'contain', 'contain', 'contain', 'contain'],
      url:
        'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
    }
  }
}
</script>
<style scoped>
.liveContent {
  display: flex
}
</style>
