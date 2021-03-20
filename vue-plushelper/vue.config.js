let proxyObj={}
proxyObj['/']={
    //  websocket
    ws: false,
    //  代理到哪里去
    target: 'http://localhost:8080',
    //  表示发送请求的请求头会被target替换
    changeOrigin: true,
    //  不重写请求部分的路径，如'/hello'
    pathRewrite:{
        '^/': '/'
    }
}

module.exports={
    devServer:{
        host: 'localhost',
        port: 8081,
        proxy: proxyObj
    }
}