<script setup>
import {User, Lock} from '@element-plus/icons-vue'
import {ref} from 'vue'

import { ElMessage } from 'element-plus'


const isRegister = ref(false)

const registerData = ref({
    username:'',
    password:'',
    rePassword:''
})

const checkRePassword = (rule,value,callback)=>{
    if(value === ''){
        callback(new Error('請再次確定密碼'))
    }else if(value !== registerData.value.password){
        callback(new Error('請確定兩次輸入的密碼一樣'))
    }else{
        callback()
    }
}

const rules={
    username:[
        { required: true, message: '請輸入用戶名', trigger: 'blur' },
        { min: 5, max: 16, message: 'Length should be 5 to 16', trigger: 'blur' }
    ],
    password:[
        { required: true, message: '請輸入密碼', trigger: 'blur' },
        { min: 5, max: 16, message: 'Length should be 5 to 16', trigger: 'blur' }
    ],
    rePassword:[
        {validator:checkRePassword,trigger:'blur'}
    ]
}

import {userRegisterService,userLoginService} from '@/api/user.js'
const register = async()=>{
    let result = await userRegisterService(registerData.value);
    ElMessage.success(result.msg ? result.msg:'注冊成功');
}

import {useTokenStore} from '@/stores/token.js'
import {useRouter} from 'vue-router'
const router = useRouter
const tokenStore = useTokenStore();
const login = async()=>{
    let result = await userLoginService(registerData.value);
    ElMessage.success(result.msg? result.msg : '登陸成功')
    tokenStore.setToken(result.data)
    router.push('/')
}

const clearRegisterData = ()=>{
    registerData.value={
        username:'',
        password:'',
        rePassword:''
    }
}
</script>


<template>
    <el-row class="login-page">
        <el-col :span="12" class="bg"></el-col>
        <el-col :span="6" :offset="3" class="form">
            <el-form ref="form" size="large" autocomplete v-if="isRegister" :model="registerData" :rules="rules">
                <el-form-item>
                    <h1>注冊</h1>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" placeholder="請輸入用戶名" v-model="registerData.username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input :prefix-icon="Lock" type="password" placeholder="請輸入密碼" v-model="registerData.password"></el-input>
                </el-form-item>
                <el-form-item prop="rePassword">
                    <el-input :prefix-icon="Lock" type="password" placeholder="請再次輸入密碼" v-model="registerData.rePassword"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="register">
                        注冊
                    </el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = false;clearRegisterData()">
                        返回
                    </el-link>
                </el-form-item>
            </el-form>
             <el-form ref="form" size="large" autocomplete="off" v-else :model="registerData" :rules="rules">
                <el-form-item>
                    <h1>登陸</h1>
                </el-form-item>
                <el-form-item prop="username">
                    <el-input :prefix-icon="User" placeholder="請輸入用戶名" v-model="registerData.username"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input name="password" :prefix-icon="Lock" type="password" placeholder="請輸入密碼" v-model="registerData.password"></el-input>
                </el-form-item>
                <el-form-item class="flex">
                    <div class="flex">
                        <el-checkbox>記住我</el-checkbox>
                        <el-link type="primary" :underline="false">忙記密碼</el-link>
                    </div>
                </el-form-item>
                <el-form-item>
                    <el-button class="button" type="primary" auto-insert-space @click="login">登陸</el-button>
                </el-form-item>
                <el-form-item class="flex">
                    <el-link type="info" :underline="false" @click="isRegister = true;clearRegisterData()">
                        注冊
                    </el-link>
                </el-form-item>
             </el-form>
        </el-col>
    </el-row>
</template>

<style lang="scss" scoped>
.login-page {
    height: 100vh;
    background-color: #fff;

    .bg {
        background: url('@/assets/logo2.jpg') no-repeat 60% center/240px auto,url(/assets/login_bg.jpg) no-repeat center/cover;
        border-radius: 0 20px 20px 0
    }

    .form {
        display: flex;
        flex-direction: column;
        justify-content: center;
        user-select: none;

        .title {
            margin: 0 auto;
        }

        .button {
            width: 100%
        }

        .flex {
            width: 100%;
            display: flex;
            justify-content: space-between;
        }
    }

}
</style>