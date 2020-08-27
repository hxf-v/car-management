export function getStorage(){
    let type = wx.getStorageSync('type')
    let name = wx.getStorageSync('name')
    let jobNumber = wx.getStorageSync('jobNumber')
    if (!type || !name || !jobNumber) {
        wx.redirectTo({
            url: '/pages/login/login',
        })
    }
}