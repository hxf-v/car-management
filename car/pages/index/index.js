import {
    Http
} from '../../utils/ip.js'
Page({

    /**
     * 页面的初始数据
     */
    data: {
      
    },
    
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        
    },
    getUnworkLog() {
        wx.request({
            url: `${Http}/get/not`,
            method: 'GET',
            data: {
                jobNum: wx.getStorageSync('jobNumber'),
            },
            success: res => {
                if (!res.data.meta.success) {
                    wx.navigateTo({
                        url: '../unworking/unworking',
                    })
                }
                else {
                    wx.navigateTo({
                        url: '../register/register',
                    })
                }
            }
        })
        // wx.request({
        //     url: `${Http}/get`,
        //     method: 'GET',
        //     data: {
        //         jobNum: wx.getStorageSync('jobNumber'),
        //     },
        //     success: res => {
        //         console.log(res)
        //         let resData = res.data.data
        //         if (resData.length === 0){
        //             wx.navigateTo({
        //                 url: '../register/register',
        //             })
        //         }
        //         resData.forEach((item) => {
        //             if (item.status === '未完成加班') {
        //                 wx.navigateTo({
        //                     url: '../unworking/unworking',
        //                 })
        //             }
        //             else{
        //                 wx.navigateTo({
        //                     url: '../register/register',
        //                 })
        //             }
        //         })
        //     }
        // })
    },
    toRegister() {
        this.getUnworkLog()
    },
    toWorkLog() {
        wx.navigateTo({
            url: '../worklog/worklog',
        })
    },
    toStatistics() {
        wx.navigateTo({
            url: '../statistics/statistics',
        })
        // wx.navigateTo({
        //     url: '../groupIndex/groupIndex',
        // })
    },
    toGroupAdd() {
        wx.navigateTo({
            url: '../groupAdd/groupAdd',
        })
    },
    toGroupSearch() {
        wx.navigateTo({
            url: '../groupSearch/groupSearch',
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
        this.setData({
            choose: wx.getStorageSync('choose')
        })
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
        this.setData({
            choose: ''
        })
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