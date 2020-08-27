import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import iView from 'iview'
import 'iview/dist/styles/iview.css'
import VueAMap from 'vue-amap'
import VideoPlayer from 'vue-video-player'
import 'videojs-contrib-hls'
import ElementUI from 'element-ui'
import api from './http/index'
import 'element-ui/lib/theme-chalk/index.css'
require('vue-video-player/src/custom-theme.css')
require('video.js/dist/video-js.css')

Vue.use(VideoPlayer)
Vue.use(VueAMap)
Vue.use(api)
VueAMap.initAMapApiLoader({
  key: 'd20f7c6925237e1da9a8eb86b7492e28',
  plugin: ['AMap.Autocomplete', 'AMap.PlaceSearch', 'AMap.Scale', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType', 'AMap.PolyEditor', 'AMap.CircleEditor'],
  v: '1.4.4',
  uiVersion: '1.0.11' // 版本号
})
Vue.config.productionTip = false
Vue.use(iView)
Vue.use(ElementUI)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
