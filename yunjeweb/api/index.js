const postRequest = (url, params) => {
    return axios({
        url: url, // 请求接口
        method: 'post', // 请求方式
        params: params
    })
}

const getRequest = (url, params) => {
    return axios({
        url: url, // 请求接口
        method: 'get', // 请求方式
        params: params
    })
}