import axios from "axios";
import { Message } from 'element-ui';

//  封装了响应拦截器
axios.interceptors.response.use(success=>{
    if(success.status && success.status==200){
        // 判断业务逻辑
        if(success.data.code == 500){
            Message.error({message:success.data.message});
            return;
        }
        if(success.data.message){
            Message.success({message:success.data.message});
        }
    }
    return success.data;
}, error => {
    if(error.response.code == 504 || error.response.code == 404){
        Message.error({message:'服务器挂掉了o(╯□╰)o'});
    }else{
        if(error.response.data.message){
            Message.error({message:error.response.data.message});
        }else{
            Message.error({message:'未知错误！'});
        }
    }
    return;
});

//  地址前缀
let base = '';

//  传送json格式的post请求
export const postRequest = (url, params)=>{
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params
    });
}

//  传送json格式的put请求
export const putRequest = (url, params)=>{
    return axios({
        method: 'put',
        url: `${base}${url}`,
        data: params
    });
}

//  传送json格式的get请求
export const getRequest = (url, params)=>{
    return axios({
        method: 'get',
        url: `${base}${url}`,
        data: params
    });
}

//  传送json格式的delete请求
export const deleteRequest = (url, params)=>{
    return axios({
        method: 'delete',
        url: `${base}${url}`,
        data: params
    });
}