import {
    HTTP
} from '../utils/http.js'

class Group extends HTTP {
    // getApproval(accountId) {
    //     return this.request({
    //         url: '/hpc/user/approval?accountId=' + accountId
    //     })
    // }
    // login(username, password) {
    //     return this.request({
    //         url: 'manage/user/login',
    //         header: {
    //             'content-type': 'application/x-www-form-urlencoded'
    //         },
    //         data: {
    //             username: username,
    //             password: password
    //         },
    //         methods: 'POST'
    //     })
    // }
    departmentAdd(department, date, record, noteAndQuestion) {
        return this.request({
            url: 'department/add',
            data: {
                department,
                date,
                record,
                noteAndQuestion
            },
            methods: 'POST'
        })
    }
    departmentGet() {
        return this.request({
            url: 'department/get'
        })
    }
}

export {
    Group
}
