// pages/register/register.js
const app = getApp()
import {
    Http
} from '../../utils/ip.js'
import { getStorage } from '../../utils/storage.js'
Page({

    /**
     * 页面的初始数据
     */
    data: {
        teacher: '',
        jobNumber: '',
        content: '',
        date: '',
        time: '',
        endDate: '',
        endTime: '',
        userInfo: {},
        hasUserInfo: false,
        canIUse: wx.canIUse('button.open-type.getUserInfo'),
        list: [],
        hasUnwork: null,
        workTime: '',
        allowBtn:true
    },
    /**
     * 生命周期函数--监听页面加载
     */
    // onLoad: function(options) {
    //     // getStorage()
    //     var value = wx.getStorageSync('jobNumber')
    //     var teacherName = wx.getStorageSync('name')
    //     this.setData({
    //         jobNumber: value,
    //         teacher: teacherName
    //     })
    //     var that = this
    //     wx.request({
    //         url: `${Http}/get/not`,
    //         method: 'GET',
    //         data: {
    //             jobNum: that.data.jobNumber,
    //         },
    //         success: res => {
    //             if (options.startTime && !res.data.meta.success){
    //                 let startDateSave = options.startTime.slice(0, 10)
    //                 let startTimeSave = options.startTime.slice(10)
    //                 let endDateSave = options.endTime !== null ? options.endTime.slice(0, 10) :
    //                     ''
    //                 let endTimeSave = options.endTime !== null ? options.endTime.slice(10) :
    //                     ''
    //                 let content = options.content
    //                 this.setData({
    //                     date: startDateSave,
    //                     time: startTimeSave,
    //                     endDate: startDateSave,
    //                     endTime: endTimeSave,
    //                     content: content
    //                 })
    //             }
    //             else if (!options.startTime && !res.data.meta.success) {
    //                 wx.redirectTo({
    //                     url: '../unworking/unworking'

    //                 })
    //             } else if (!options.startTime && res.data.meta.success) {
    //                 this.quickSetTime()
    //             }
    //         }
    //     })
    //     // wx.request({
    //     //     url: `${ Http }/get`,
    //     //     method: 'GET',
    //     //     data: {
    //     //         jobNum: that.data.jobNumber,
    //     //     },
    //     //     success: res => {
    //     //         this.setData({
    //     //             list: res.data.data
    //     //         })
    //     //         // ================ //
    //     //         this.hasUnworked()

    //     //         if (options.startTime && this.data.hasUnwork) {
    //     //             let startDateSave = options.startTime.slice(0, 10)
    //     //             let startTimeSave = options.startTime.slice(10)
    //     //             let endDateSave = options.endTime !== null ? options.endTime.slice(0, 10) :
    //     //                 ''
    //     //             let endTimeSave = options.endTime !== null ? options.endTime.slice(10) :
    //     //                 ''
    //     //             let content = options.content
    //     //             this.setData({
    //     //                 date: startDateSave,
    //     //                 time: startTimeSave,    
    //     //                 endDate: startDateSave,
    //     //                 endTime: endTimeSave,
    //     //                 content: content
    //     //             })
    //     //         } else if (!options.startTime && this.data.hasUnwork) {
    //     //             wx.redirectTo({
    //     //                 url: '../unworking/unworking'

    //     //             })
    //     //         } else if (!options.startTime && !this.data.hasUnwork) {
    //     //             this.quickSetTime()
    //     //         }
    //     //     }
    //     // })
    //     if (app.globalData.userInfo) {
    //         this.setData({
    //             userInfo: app.globalData.userInfo,
    //             hasUserInfo: tr
    //         })
    //     } else if (this.data.canIUse) {
    //         // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
    //         // 所以此处加入 callback 以防止这种情况
    //         app.userInfoReadyCallback = res => {
    //             this.setData({
    //                 userInfo: res.userInfo,
    //                 hasUserInfo: true
    //             })
    //         }
    //     } else {
    //         // 在没有 open-type=getUserInfo 版本的兼容处理
    //         wx.getUserInfo({
    //             success: res => {
    //                 app.globalData.userInfo = res.userInfo
    //                 this.setData({
    //                     userInfo: res.userInfo,
    //                     hasUserInfo: true
    //                 })
    //             }
    //         })
    //     }
    // },
    hasUnworked() {
        this.data.list.map((item, index) => {
            if (item.status === '未完成加班') {
                this.setData({
                    hasUnwork: true
                })
            }
        })
    },
    //改时间格式
    GMTToStr(time) {
        let date = new Date(time)
        let Str = date.getFullYear() + '/' +
            (date.getMonth() + 1) + '/' +
            date.getDate() + ' ' +
            date.getHours() + ':' +
            date.getMinutes()
        return Str
    },

    getUserInfo: function(e) {
        app.globalData.userInfo = e.detail.userInfo
        this.setData({
            userInfo: e.detail.userInfo,
            hasUserInfo: true
        })
    },
    bindTextAreaBlur: function(e) {
        this.setData({
            content: e.detail.value
        })
    },
    bindDateChange: function(e) {
        this.setData({
            date: e.detail.value,
            endDate: e.detail.value
        })
    },
    bindTimeChange: function(e) {
        this.setData({
            time: e.detail.value + ':00'
        })
    },
    bindEndTimeChange: function(e) {
        this.setData({
            endDate: this.data.date,
            endTime: e.detail.value + ':00'
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
        return currentdate;
    },
    quickSetTime() {
        var myDate = new Date()
        var nowTime = myDate.toLocaleTimeString('chinese', {
            hour12: false
        }).slice(0, 8)
        let nowDate = this.getNowFormatDate()
        this.setData({
            date: nowDate,
            time: nowTime,
            endDate: nowDate,
            endTime: ''
        })
    },
    //保存当前加班记录
    saveWorkInfo() {
        this.setData({
            allowBtn: false
        })
        if (!this.data.date || !this.data.time) {
            wx.showToast({
                title: '请完善信息',
                icon: 'loading',
                duration: 1500
            })
            return
        }
        if (this.data.endTime !== '' && new Date((this.data.endDate + ' ' + this.data.endTime).replace(/\.[\d]{3}Z/, '').replace(/(-)/g, '/')) - new Date((this.data.date + ' ' + this.data.time).replace(/\.[\d]{3}Z/, '').replace(/(-)/g, '/')) < 0) {
            wx.showToast({
                title: '时间有误',
                icon: 'loading',
                duration: 1500
            })
            return
        }
        wx.request({
            url: `${Http}/add`,
            method: 'POST',
            data: {
                jobNum: this.data.jobNumber,
                startTimeStr: this.data.date + ' ' + this.data.time,
                endTimeStr: this.data.endDate === '' ? null : this.data.endDate + ' ' + this.data.endTime,
                content: this.data.content,
                name: this.data.teacher,
                state: '否'
            },
            success: res => {
                wx.showToast({
                    mask:true,
                    title: '保存成功',
                    icon: 'success',
                    duration: 1500
                    // success: function(){
                    //     this.setData({
                    //         allowBtn: true
                    //     })
                    // }
                })
                setTimeout(() => {
                    wx.redirectTo({
                        url: '../worklog/worklog'
                    })
                }, 200)

            }
        })
    },
    //提交加班记录
    submitWorkInfo() {
        this.setData({
            allowBtn: false
        })
        if (!this.data.date || !this.data.time || !this.data.endDate || !this.data.endTime) {
            wx.showToast({
                title: '请完善全部信息',
                icon: 'loading',
                duration: 1500
            })
            return
        }
        if (this.data.endTime !== '' && new Date((this.data.endDate + ' ' + this.data.endTime).replace(/\.[\d]{3}Z/, '').replace(/(-)/g, '/')) - new Date((this.data.date + ' ' + this.data.time).replace(/\.[\d]{3}Z/, '').replace(/(-)/g, '/')) < 0) {
            wx.showToast({
                title: '时间有误',
                icon: 'loading',
                duration: 1500
            })
            return
        }
        let workTime = this.computeWorkTime(this.data.time, this.data.endTime)
        this.setData({
            workTime
        })
        wx.request({
            url: `${Http}/add`,
            method: 'POST',
            data: {
                jobNum: this.data.jobNumber,
                startTimeStr: this.data.date + ' ' + this.data.time,
                endTimeStr: this.data.endDate === '' ? null : this.data.endDate + ' ' + this.data.endTime,
                content: this.data.content,
                name: this.data.teacher,
                workTime:this.data.workTime,
                state: '是'
            },
            success: res => {
                wx.showToast({
                    mask: true,
                    title: '提交成功',
                    icon: 'success',
                    duration: 1500
                })
                setTimeout(() => {
                    wx.switchTab({
                        url: '/pages/index/index'
                    })
                }, 500)

            }
        })
    },
    //计算时间差 -分钟
    computeWorkTime(beginTime, endTime) {
        let begin = beginTime.replace(/\s+/g, "")
        let end = endTime.replace(/\s+/g, "")
        let beginHour = parseInt(begin.slice(0, 2))
        let beginMin = parseInt(begin.slice(3, 5))
        let endHour = parseInt(end.slice(0, 2))
        let endMin = parseInt(end.slice(3, 5))
        return (endHour * 60 + endMin) - (beginHour * 60 + beginMin)
    },
    //返回首页
    backToHome() {
        wx.reLaunch({
            url: '../index/index',
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