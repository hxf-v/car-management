import {
    Group
} from '../../models/group.js'
import { config } from '../../config.js'
const group = new Group()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        groupName: '',
        date:'',
        groupWorkDetail: '',
        groupProblem: '',
        account: '',
        userName: '',
        password: '',
        plateNum: ''
    },
    accountInput(e) {
        this.setData({
            account: e.detail.detail.value
        })
    },
    userNameInput(e) {
        this.setData({
            userName: e.detail.detail.value
        })
    },
    passwordInput(e) {
        this.setData({
            password: e.detail.detail.value
        })
    },
    plateNumInput(e) {
        this.setData({
            plateNum: e.detail.detail.value
        })
    },
    bindDateChange: function (e) {
        this.setData({
            date: e.detail.value
        })
    },
    groupWorkDetailInput(e) {
        this.setData({
            groupWorkDetail: e.detail.value
        })
    },
    groupProblemInput(e) {
        this.setData({
            groupProblem: e.detail.value
        })
    },
    getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        this.setData({
            date: currentdate
        })
    },
    submitGroup() {
        if(this.data.date == '' || this.data.account == '' || this.data.userName == '' || this.data.password == '' || this.data.plateNum == '') {
            wx.showToast({
                title: '请完善所有信息',
                icon: 'loading',
                duration: 1000
              })
              return
        } else {
            wx.request({
              url: config.api_base_url+'car/user/register ',
              method: 'POST',
              header: {
                  'content-type': 'application/json'
              },
              data: {
                  timeStr: this.data.date,
                  account: this.data.account,
                  userName: this.data.userName,
                  password: this.data.password,
                  plateNum: this.data.plateNum
              },
              success: res => {
                  console.log(res)
                  if(res.data.meta.success) {
                    this.setData({
                        account: '',
                        userName:'',
                        password: '',
                        plateNum: ''
                    })
                    wx.showToast({
                        mask: true,
                        title: '注册成功',
                        icon: 'success',
                        duration: 5000
                    })
                    wx.redirectTo({
                        url: '/pages/login/login'
                    })
                  } else {
                    wx.showModal({
                        content: res.data.meta.message,
                        title: '提示',
                        showCancel: false
                    })
                  }
              }
            })
        }
        // group.departmentAdd(this.data.groupName, this.data.date, this.data.groupWorkDetail, this.data.groupProblem).then(res => {
        //     console.log(res)
        //     if (res.meta.success){
        //         wx.showToast({
        //             mask: true,
        //             title: '提交成功',
        //             icon: 'success',
        //             duration: 1500
        //         })
        //         this.setData({
        //             groupWorkDetail: '',
        //             groupProblem: ''
        //         })
        //     }
        // })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.getNowFormatDate()
        this.setData({
            groupName: wx.getStorageSync('group')
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