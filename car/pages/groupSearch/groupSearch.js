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
    beginDate: '',
    endDate: '',
    current: '全部',
    groupsName: ['控制记录', '设置记录', '设备异常记录', '平台登入记录',  '服务器日志'],
    allList: [],
    allRecord: '',
    groupList: [],
    collapseName: '网络工程部'
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
      endDate: currentdate
    })
  },
  //获取一周前日期
  getLastWeekDate() {
    var oneweekdate = new Date(new Date() - 7 * 24 * 3600 * 1000);
    var y = oneweekdate.getFullYear();
    var m = oneweekdate.getMonth() + 1;
    var d = oneweekdate.getDate();
    if (m >= 1 && m <= 9) {
      m = "0" + m;
    }
    if (d >= 0 && d <= 9) {
      d = "0" + d;
    }
    var formatwdate = y + '-' + m + '-' + d
    this.setData({
      beginDate: formatwdate
    })
  },
  //更改起始日期
  beginDateChange(e) {
    this.setData({
      beginDate: e.detail.value,
    })
  },
  //更改结束日期
  endDateChange(e) {
    this.setData({
      endDate: e.detail.value,
    })
  },
  //获取列表
  getList() {
    group.departmentGet().then(res => {
      this.setData({
        allList: res.data,
        groupList: res.data
      })
    })
  },
  computeDays(date) {
    return Math.floor(date.slice(0, 4) * 365 +
      date.slice(5, 7) * 30.4 +
      parseInt(date.slice(8, 10)))

  },
  // 时间查询
  searchList() {
    const beginDays = this.computeDays(this.data.beginDate)
    const endDays = this.computeDays(this.data.endDate)
    if (beginDays > endDays) {
      wx.showToast({
        title: '时间有误',
        icon: 'loading',
        duration: 1500
      })
    } else {
      wx.request({
        url: config.api_base_url+'car/user/historyrecord   ',
        method: 'POST',
        header: {
            'content-type': 'application/json'
        },
        data: {
            // plateNum: "皖ABC999",
            plateNum: wx.getStorageSync('plateNum'),
            startTimeStr: this.data.beginDate + ' ' + '00:00:00',
            endTimeStr: this.data.endDate + ' ' + '00:00:00'
        },
        success: res => {
            console.log(res)
            let allRecordList = []
            res.data.data.forEach(item => {
              allRecordList.push(item.wholeRouteRecord)
            })
            this.setData({
                allRecord : allRecordList
            })
        }
    })
      // let seletedTimeList = []
      // group.departmentGet().then(value => {
      //   value.data.forEach(res => {
      //     if (beginDays <= this.computeDays(res.date) && this.computeDays(res.date) <= endDays) {
      //       seletedTimeList.push(res)
      //     }
      //   })
      //   this.setData({
      //     allList: seletedTimeList
      //   })
      //   this.getIndexList()
      //   if (this.data.current === '全部') {
      //     this.computeAllTabs()
      //   } else {
      //     this.computeTabs()
      //   }
      // })

    }
  },
  //选择 '全部' tabs
  computeAllTabs() {
    this.setData({
      groupList: this.data.allList
    })
  },
  //选择其他 tabs
  computeTabs() {
    let groupRes = []
    this.data.allList.forEach(res => {
      if (res.department === this.data.current) {
        groupRes.push(res)
      }
    })
    this.setData({
      groupList: groupRes
    })
  },
  //切换tab
  switchTab({
    detail
  }) {
    this.setData({
      current: detail.key
    })
    if (detail.key === '全部') {
      this.computeAllTabs()
    } else {
      this.computeTabs()
    }

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.getLastWeekDate()
    this.getNowFormatDate()
    this.getList()
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