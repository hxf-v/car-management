// components/infoList/index.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    jobNumber: {
      type: String
    },
    startTime: {
      type: String,
      observer(value) {
        let newStartTime = value.replace(/(-)/g, '/').slice(0,16)
        this.setData({
          newStartTime: newStartTime
        })
      }
    },
    endTime: {
      type: String,
      observer(value) {
        let newEndTime = value.replace(/(-)/g, '/').slice(0, 16)
        this.setData({
          newEndTime: newEndTime
        })
      }
    },
    content: {
      type: String
    },
    state: {
      type: String
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    newStartTime:'',
    newEndTime:''
  },

  /**
   * 组件的方法列表
   */
  methods: {
    
  }
})