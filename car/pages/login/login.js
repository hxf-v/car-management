import {
  config
} from '../../config.js'
Page({
  data: {
    jobNumber: '',
    idCard: '',
    animationData: {}
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.getStorage({
      key: 'jobNumber',
      success: (res) => {
        wx.switchTab({
          url: '/pages/statistics/statistics'
        })
      },
      fail: err => {
        console.log(err)
      }
    })
  },
  jobNumInput(e) {
    this.setData({
      jobNumber: e.detail.value
    })
  },
  idInput(e) {
    this.setData({
      idCard: e.detail.value
    })
  },
  formReset() {
    wx.navigateTo({
      url: '/pages/groupAdd/groupAdd'
    })
  },
  formSubmit(e) {
    if (e.detail.value.jobNumber === '' || e.detail.value.idCard === '') {
      wx.showToast({
        title: '请完善信息',
        icon: 'loading',
        duration: 1000
      })
      return
    }
    wx.showLoading({
        title: '登录中',
    })
    wx.request({
        // url: `${ Http }/login`,
        url: config.api_base_url+'car/user/login',
        method: 'POST',
        header: {
            'content-type': 'application/json'
        },
        data: {
            account: this.data.jobNumber,
            password: this.data.idCard
        },
        success: res => {
            console.log(res)
            wx.hideLoading()
            if (res.data.meta.success) {
              // wx.setStorageSync('id', res.data.data.userInfo ? res.data.data.userInfo.id : '')
              // wx.setStorageSync('jobNumber', res.data.data.userInfo ? res.data.data.userInfo.account : '')
              // wx.setStorageSync('name', res.data.data.userInfo ? res.data.data.userInfo.userName : '')
              // wx.setStorageSync('time', res.data.data.userInfo ? res.data.data.userInfo.time : '')
              // wx.setStorageSync('state', res.data.data.userInfo ? res.data.data.userInfo.state : '')
              // wx.setStorageSync('plateNum', res.data.data.userInfo ? res.data.data.userInfo.plateNum : '')
              // wx.setStorageSync('img', res.data.data.schoolCarRegister ? res.data.data.schoolCarRegister.imgs : '')
                wx.setStorage({
                    key: "type",
                    data: 'admin'
                })
                wx.setStorage({
                  key: "id",
                  data: res.data.data.userInfo.id
              })
                wx.setStorage({
                    key: "jobNumber",
                    data: res.data.data.userInfo.account
                })
                wx.setStorage({
                  key: "pwd",
                  data: res.data.data.userInfo.password
              })
                wx.setStorage({
                    key: "name",
                    data: res.data.data.userInfo.userName
                })
                wx.setStorage({
                  key: "time",
                  data: res.data.data.userInfo ? res.data.data.userInfo.time : ''
              })
              wx.setStorage({
                key: "state",
                data: res.data.data.userInfo ? res.data.data.userInfo.state : ''
              })
              wx.setStorage({
                key: "plateNum",
                data: res.data.data.userInfo.plateNum
              })
              wx.setStorage({
                key: "img",
                data:  res.data.data.schoolCarRegister ? res.data.data.schoolCarRegister.imgs : ''
              })
                wx.setStorage({
                    key: "group",
                    data: res.data.data.schoolCarRegister ? res.data.data.schoolCarRegister.department : ''
                })
                wx.reLaunch({
                  url: '/pages/statistics/statistics',
                })
            } else {
                wx.showToast({
                    title: '工号或密码错误',
                    icon: 'loading',
                    duration: 2000
                })
            }
        }
    })

  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var animation = wx.createAnimation({
      duration: 2000,
      timingFunction: 'ease',

    })

    this.animation = animation
    animation.translateX(250).scale(1.1, 1.1).step()
    animation.scale(1, 1).step()
    this.setData({
      animationData: animation.export()
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})