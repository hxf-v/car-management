// pages/editCar/editCar.js
import { config } from '../../config.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    account: '',
    plateNum: ''
  },
  accountInput(e) {
    this.setData({
        account: e.detail.detail.value
    })
  },
  plateNumInput(e) {
    this.setData({
      plateNum: e.detail.detail.value
    })
  },
  submitGroup() {
    if(this.data.account == '' || this.data.plateNum == '') {
        wx.showToast({
            title: '请完善所有信息',
            icon: 'loading',
            duration: 1000
          })
          return
    } else {
        wx.request({
          url: config.api_base_url+'car/user/platenum ',
          method: 'POST',
          header: {
              'content-type': 'application/json'
          },
          data: {
              account: this.data.account,
              plateNum: this.data.plateNum,
          },
          success: res => {
            console.log(res)
            if(res.data.meta.success) {
              wx.showToast({
                mask: true,
                title: '修改成功',
                icon: 'success',
                duration: 2000
              })
              wx.setStorage({
                key: "plateNum",
                data: this.data.plateNum
              })
              this.setData({
                plateNum: ''
              })
            }
            wx.clearStorageSync()
            wx.redirectTo({
              url: '/pages/login/login',
            })
          }
        })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      account: wx.getStorageSync('jobNumber'),
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