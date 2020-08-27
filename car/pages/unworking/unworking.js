// pages/unworking/unworking.js
import {
    Http
} from '../../utils/ip.js'
Page({

    /**
     * 页面的初始数据
     */
    data: {

    },
    getUnworkLog() {
        var value = wx.getStorageSync('jobNumber')
        this.setData({
            jobNumber: value
        })
        var that = this
        wx.request({
            url: `${Http}/get`,
            method: 'GET',
            data: {
                jobNum: that.data.jobNumber,
            },
            success: res => {
                let resData = res.data.data
                let showData = []
                resData.forEach((item) => {
                    if (item.status === '未完成加班') {
                        showData.push(item)
                    }
                })
                this.setData({
                    list: showData
                })
            }
        })
    },
    onselect(e) {
        wx.redirectTo({
            url: '../register/register?startTime=' + e.target.dataset.starttime +
                '&&endTime=' + e.target.dataset.endtime + '&&content=' + e.target.dataset.content
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        this.getUnworkLog()
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