// pages/worklog/worklog.js
import {
    Http
} from '../../utils/ip.js'
import {
    getStorage
} from '../../utils/storage.js'
Page({

    /**
     * 页面的初始数据
     */
    data: {
        animationData: {},
        jobNumber: '',
        list: [],
        hasUnwork: null,
        cardTitle: '当月加班时间',
        cardTime: 'ssss',
        contentInfo: 'wss',
        cardHeight: '1rpx',
        selectDate: ''
    },
    hasUnworked() {
        this.data.list.map((item, index) => {
            if (item.status === '未完成加班') {
                this.setData({
                    hasUnwork: true
                })
                this.cardHeight()
            }
        })
    },
    onselect(e) {
        if (e.target.dataset.state === '未完成加班') {
            wx.redirectTo({
                url: '../register/register?startTime=' + e.target.dataset.starttime +
                    '&&endTime=' + e.target.dataset.endtime + '&&content=' + e.target.dataset.content
            })
        }
    },
    getHeight() {
        const query = wx.createSelectorQuery()
        query.select('.list-container-no').boundingClientRect()
        query.selectViewport().scrollOffset()
        query.exec(function(res) {})
    },
    cardHeight() {
        if (this.data.hasUnwork) {
            this.setData({
                cardHeight: '240rpx'
            })
        }
    },
    getList() {
        var value = wx.getStorageSync('jobNumber')
        let name = wx.getStorageSync('name')
        this.setData({
            jobNumber: value,
            contentInfo: name + '老师'
        })
        var that = this
        wx.request({
            url: `${Http}/get`,
            method: 'GET',
            data: {
                jobNum: that.data.jobNumber,
            },
            success: res => {
                // let resdata = res.data.data
                // resdata.sort((v1, v2) => {
                //     return v1.startTimeStr > v2.startTimeStr
                // })
                // console.log(resdata)
                console.log(res)
                this.setData({
                    list: res.data.data.reverse()
                })
                this.hasUnworked()
                this.getNowFormatDate()
                this.computeMonthTime()
                this.computeList()
            }
        })
    },
    //获取当前日期
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
            selectDate: currentdate.slice(0, 10),
        })
    },
    //处理月份时长
    computeMonthTime() {
        console.log(this.data.selectDate)
        console.log(this.data.list)
        let m = 0
        this.data.list.forEach((value) => {
            if (value.startTimeStr.slice(0, 7) === this.data.selectDate) {
                value.workTime ? m = m + value.workTime : m = m + 0
            }
        })
        this.setData({
            cardTime: parseInt(m) + '分钟' || '0小时'
        })
    },
    // 计算天数
    dateTodays(date) {
        return date.slice(0, 4) * 365 +
            date.slice(5, 7) * 30 +
            parseInt(date.slice(8, 10))
    },
    // 截取最近两月的记录
    computeList() {
        let currentDays = this.dateTodays(this.data.selectDate)
        let arr = []
        this.data.list.forEach(value => {
            if (currentDays - (this.dateTodays(value.startTimeStr)) < 62) {
                arr.push(value)
            }
        })
        this.setData({
            list: arr
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        getStorage()
        this.getHeight()
        this.getList()

    },
    //改时间格式
    // GMTToStr(time) {
    //   let date = new Date(time)
    //   let Str = date.getFullYear() + '/' +
    //     (date.getMonth() + 1) + '/' +
    //     date.getDate() + ' ' +
    //     date.getHours() + ':' +
    //     date.getMinutes()
    //   return Str
    // },
    // GMTToStr(timestamp) {
    //   var timeStr = timestamp.replace("T", " ").replace(/-/g, "/");
    //   //var timeStr = this.timeFormat(timestamp)
    //   //var timeStr = this.strToDate(timestamp)
    //   console.log('timestr',timeStr)
    //   var date = new Date(timeStr);
    //   console.log('date',date)
    //   var Y = date.getFullYear() + '/';
    //   var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '/';
    //   var D = date.getDate() + ' ';
    //   var h = date.getHours() + ':';
    //   var m = date.getMinutes() + ':';
    //   var s = date.getSeconds();
    //   return Y + M + D + h + m + s;
    // },
    // timeFormat(timeStr){//返回时间戳毫秒数。原数据为2017-12-07T18:55:12
    //   timeStr = timeStr.replace("T", " ").replace(/[-]/g, "/");
    //   console.log('timestr',timeStr)
    //   var timestamp = Date.parse(new Date(timeStr));
    //   return timestamp;
    // },

    //后台改字符串 用此方法
    // strToDate(dateObj) {
    //   dateObj = dateObj.replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '').replace(/(-)/g, '/')
    //   dateObj = dateObj.slice(0, dateObj.indexOf("."))
    //   return new Date(dateObj)
    // },
    toWriteInfo() {
        wx.redirectTo({
            url: '../register/register',
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
        var animation = wx.createAnimation({
            duration: 2000,
            timingFunction: 'ease',
        })

        this.animation = animation
        animation.opacity(0.7).step()
        animation.opacity(1).step()

        this.setData({
            animationData: animation.export()
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