import {
    config
} from '../config.js'
const errTips = {
    1: '抱歉'
}
class HTTP {
    request({
        url,
        data = {},
        methods = 'GET',
        header = {}
    }) {
        return new Promise((resolve, reject) => {
            this._request(url, resolve, reject, data, methods, header)
        })
    }
    _request(url, resolve, reject, data = {}, methods = 'GET', header = {}) {
        wx.request({
            url: config.api_blink_url + url,
            data: data,
            method: methods,
            header: header,
            success: (res) => {
                resolve(res.data)
            },
            fail: (err) => {
                reject()
                wx.showToast({
                    title: '好像哪里错了~~',
                    icon: 'none',
                    duration: 1500
                })
            }
        })
    }

    _show_error(err_code) {
        if (!err_code) {
            err_code = 1
        }
        wx.showToast({
            title: errTips[err_code] || '哪里出错了',
            icon: 'none',
            duration: 1500
        })
    }
}

export {
    HTTP
}