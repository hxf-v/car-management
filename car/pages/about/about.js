// pages/about/about.js
const app = getApp()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        chepai: '',
        account: '',
        choose: '',
        teacher: '',
        userInfo: {},
        hasUserInfo: false,
        canIUse: wx.canIUse('button.open-type.getUserInfo')
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        this.setData({
            teacher: wx.getStorageSync('name'),
            chepai: wx.getStorageSync('plateNum'),
            account: wx.getStorageSync('jobNumber'),
            choose: wx.getStorageSync('choose')
        })
    },
    editPwd() {
        wx.navigateTo({
          url: '/pages/editPwd/editPwd',
        })
    },
    changeCar() {
        wx.navigateTo({
            url: '/pages/editCar/editCar',
          })
    },
    getUserInfo: function(e) {
        console.log(e)
        app.globalData.userInfo = e.detail.userInfo
        this.setData({
            userInfo: e.detail.userInfo,
            hasUserInfo: true
        })
    },
    goOther() {
        wx.removeStorage({
            key: 'choose',
            success(res) {
                wx.redirectTo({
                    url: '../choose/choose'
                })
            }
        })
        
    },
    logout() {
        wx.clearStorageSync()
        // wx.removeStorageSync('jobNumber')
        // wx.removeStorageSync('pwd')
        // wx.removeStorageSync('name')
        // wx.removeStorageSync('plateNum')
        wx.redirectTo({
            url: '/pages/login/login',
        })
    },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function() {
        
    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function() {
        // this.setData({
        //     teacher: wx.getStorageSync('name'),
        //     chepai: wx.getStorageSync('plateNum'),
        //     account: wx.getStorageSync('jobNumber'),
        // })
    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function() {

    }
})