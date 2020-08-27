import {
    Http
} from '../../utils/ip.js'
import {
    getStorage
} from '../../utils/storage.js'
import { base64src } from '../../utils/car.js'
import { config } from '../../config.js'
Page({

    /**
     * 页面的初始数据
     */
    data: {
        state: '',
        time: '',
        newtime: '',
        name: '',
        password: '',
        jobNumber: '',
        id: '',
        chepai: '',
        img: '',
        imgCar: '',
        DrivingData: '',
        ParkData: '',
        visible2: false,
        visible1: false,
        list: '',
        selectDate: '',
        cardTitle: '加班时长',
        cardTime: '',
        admin: false,
        startDate: '',
        startDateShow: '',
        endDate: '',
        endDateShow: '',
        rank: [],
        timeSort: '倒序',
        numSort: '倒序',
        timeSortClass: 'header-user-btn-ac',
        numSortClass: 'header-user-btn',
    },
    //判断是否是管理员
    youAreAdmin() {
        try {
            const value = wx.getStorageSync('type')
            if (value === 'admin') {
                this.setData({
                    admin: true
                })
            }
        } catch (e) {
            wx.redirectTo({
                url: '/pages/login/login'
            })
        }
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
        let nowstr = currentdate.split('-')[0] + '年' + currentdate.split('-')[1] + '月'
        this.setData({
            cardTitle: nowstr,
            selectDate: currentdate.slice(0, 7),
            startDate: currentdate.slice(0, 5) + month + '-01' + ' ' + '00:00:00',
            endDate: currentdate + ' ' + '23:59:59'
        })
        return currentdate;
    },
    //排行榜 起始时间
    beginDateChange(e) {
        const t = e.detail.value + ' ' + '00:00:00'
        this.setData({
            startDate: t
        })
    },
    //排行榜 结束时间
    endDateChange(e) {
        const v = e.detail.value + ' ' + '23:59:59'
        this.setData({
            endDate: v
        })
    },
    //获取排行榜
    searchList() {
        this.getRank()
    },
    handleClick() {
        this.setData({
            visible1: true
        })
    },
    handleClose1() {
        this.setData({
            visible1: false
        })
    },
    handleClick2() {
        this.setData({
            visible2: true
        })
    },
    handleClose2() {
        this.setData({
            visible2: false
        })
    },
    // 获取排行榜
    getRank() {
        this.setData({
            isLoading: true
        })
        wx.request({
            url: `${Http}/rank`,
            method: 'POST',
            data: {
                startDate: this.data.startDate,
                endDate: this.data.endDate
            },
            header: {
                'content-type': 'application/x-www-form-urlencoded'
            },
            success: res => {
                console.log(res)
                this.setData({
                    rank: res.data.data,
                    isLoading: false
                })
            }
        })
    },
    //获取数据
    getList() {
        wx.request({
            url: `${Http}/get`,
            method: 'GET',
            data: {
                jobNum: wx.getStorageSync('jobNumber')
            },
            success: res => {
                console.log(res)
                this.setData({
                    list: res.data.data,
                    contentInfo: res.data.data ? '姓名' + ':  ' + res.data.data[0].name : '暂无数据',
                    footInfo: res.data.data ? '工号' + ':  ' + res.data.data[0].jobNum : '暂无数据',
                })
                //this.getAlltime()
                this.computeMonthTime()
            }
        })
    },
    //获取当前月份总时长
    // getAlltime() {
    //     let workTime = 0
    //     this.data.list.forEach((value) => {
    //         workTime = workTime + value.workTime
    //     })
    //     let workTimeShow = Math.floor(workTime / 60) + '小时' + workTime % 60 + '分钟'
    //     this.setData({
    //         cardTime: workTimeShow
    //     })
    // },
    //选择月份
    dateChange(v) {
        let str = v.detail.value.split('-')[0] + '年' + v.detail.value.split('-')[1] + '月'
        this.setData({
            selectDate: v.detail.value,
            cardTitle: str
        })
        this.computeMonthTime()
    },
    //切换为总时长 
    // clickImage() {
    //     this.getAlltime()
    //     this.setData({
    //         cardTitle: '总加班时长',
    //         selectDate: '2018-12'
    //     })
    // },
    sortNum() {
        this.setData({
            timeSortClass: 'header-user-btn',
            numSortClass: 'header-user-btn-ac'
        })
        if (this.data.numSort === '正序') {
            let rank = this.data.rank
            rank.sort((value1, value2) => {
                return value1.jobNum < value2.jobNum
            })
            this.setData({
                rank,
                numSort: '倒序'
            })
            return
        }
        if (this.data.numSort === '倒序') {
            let rank = this.data.rank
            rank.sort((value1, value2) => {
                return value1.jobNum > value2.jobNum
            })
            this.setData({
                rank,
                numSort: '正序'
            })
            return
        }

    },
    sortTime() {
        this.setData({
            timeSortClass: 'header-user-btn-ac',
            numSortClass: 'header-user-btn'
        })
        if (this.data.timeSort === '正序') {
            let rank = this.data.rank
            rank.sort((value1, value2) => {
                return value1.value < value2.value
            })
            this.setData({
                rank,
                timeSort: '倒序'
            })
            return
        }
        if (this.data.timeSort === '倒序') {
            let rank = this.data.rank
            rank.sort((value1, value2) => {
                return value1.value > value2.value
            })
            this.setData({
                rank,
                timeSort: '正序'
            })
            return
        }

    },
    //处理月份时长
    computeMonthTime() {
        let m = 0
        this.data.list.forEach((value) => {
            if (value.startTimeStr.slice(0, 7) === this.data.selectDate) {
                value.workTime ? m = m + value.workTime : m = m + 0
            }
        })
        let workTimeShow = Math.floor(m / 60) + '小时' + m % 60 + '分钟'
        this.setData({
            cardTime: workTimeShow
        })
    },
    getPark() {
        wx.request({
            url: config.api_base_url + 'car/user/parkinglot ',
            method: 'GET',
            success: res => {
                this.setData({
                    ParkData: res.data.data
                })
            }
        })
        console.log(this.data.ParkData)
    },
    getDriving() {
        wx.request({
            url: config.api_base_url + 'car/user/realtimerecord  ',
            method: 'POST',
            header: {
                'content-type': 'application/json'
            },
            data: {
                plateNum: wx.getStorageSync('plateNum')
            },
            success: res => {
                this.setData({
                    DrivingData: res.data.data
                })
            }
        })
    },
    getTime() {
        if(this.data.newtime) {
            this.setData({
                time: this.data.newtime.substring(0,10)
            })
        } else {
            this.setData({
                time: ''
            })
        }
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        // getStorage()
        // this.youAreAdmin()
        // this.getNowFormatDate()
        // this.getList()
        // this.getRank()
        this.getPark()
        this.getDriving()
        this.setData({
            state: wx.getStorageSync('state'),
            newtime: wx.getStorageSync('time'),
            name: wx.getStorageSync('name'),
            jobNumber: wx.getStorageSync('jobNumber'),
            id: wx.getStorageSync('id'),
            chepai: wx.getStorageSync('plateNum'),
            imgCar: wx.getStorageInfoSync('img')
            // img: wx.getStorageSync('img'),
        })
        this.getTime()
        // base64src(this.data.img, res => {
        //     console.log(res) // 返回图片地址，直接赋值到image标签即可
        //     this.setData({
        //         imgCar: res
        //     })
        // });
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
        // this.setData({
        //     state: wx.getStorageSync('state'),
        //     time: wx.getStorageSync('time'),
        //     name: wx.getStorageSync('name'),
        //     jobNumber: wx.getStorageSync('jobNumber'),
        //     id: wx.getStorageSync('id'),
        //     chepai: wx.getStorageSync('plateNum'),
        //     img: wx.getStorageSync('img'),
        // })
        // base64src(this.data.img, res => {
        //     console.log(res) // 返回图片地址，直接赋值到image标签即可
        //     this.setData({
        //         imgCar: res
        //     })
        // });
        // this.getPark()
        // this.getDriving()
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