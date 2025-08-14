<template>
  <div class="register">
    <div class="container">
      <el-form :model="form"
               label-width="auto"
               :inline="false"
               ref="formUser"
               label-position="top"
               :rules="rules"
      >
        <el-row :gutter="20">
          <el-col>
            <el-form-item label="Username" prop="username">
              <el-input v-model="form.username" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="Password" prop="password">
              <el-input v-model="form.password" :show-password="true" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="Retype password" prop="retypePassword">
              <el-input v-model="form.retypePassword" :show-password="true" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="Age" prop="age">
              <el-input v-model="form.age" type="number" />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="StartTime" prop="startTime">
              <el-date-picker
                  v-model="form.startTime"
                  type="datetime"
                  placeholder="Select start date and time"
                  format="YYYY-MM-DD HH:mm:ss"
                  value-format="YYYY-MM-DD HH:mm:ss"
              />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="End Time" prop="endTime">
              <el-date-picker
                  v-model="form.endTime"
                  type="datetime"
                  format="YYYY-MM-DD HH:mm:ss"
                  value-format="YYYY-MM-DD HH:mm:ss"
                  placeholder="Select end date and time"
              />
            </el-form-item>
          </el-col>
          <el-col>
            <el-form-item label="Avatar" prop="avatar">
              <el-upload accept="image/*"
                         :show-file-list="false"
                         :auto-upload="false"
                         list-type="picture"
                         :on-change="handeChange">
                <el-button type="primary" :icon="Upload">Upload Avatar</el-button>
              </el-upload>
            </el-form-item>
            <div class="preview-image" v-if="form.imageUrl">
              <el-image :src="form.imageUrl" class="preview-image" fit="contain"/>
            </div>
          </el-col>
          <el-col>
              <el-button type="primary" @click="handleSubmitForm">Submit</el-button>
              <el-button type="danger" @click="handleCancel">Cancel</el-button>
          </el-col>
        </el-row>
      </el-form>
    </div>
  </div>
</template>

<script setup lang="ts">

  import {Upload} from "@element-plus/icons-vue";
  import { reactive, ref, defineExpose, watchEffect } from 'vue'
  import type { FormRules } from 'element-plus'

  const form = reactive({
    username: '',
    password: '',
    retypePassword: '',
    age: null as number | null,
    startTime: '',
    endTime: '',
    avatar:null,
    imageUrl:''
  });

  const formUser = ref();
  const isUpdate = ref<boolean>(false);

  let regexPassword = new RegExp('^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>]).+$');

  const ValidatorUsername=(rule:unknown,value:string,callback:(error?:Error)=>void)=>{
    if(value.length < 6){
      callback(new Error("Username must more than six character!"))
    }else{
      callback();
    }
  }

  const ValidatorPassword=(rule:unknown,value:string,callback:(error?:Error)=>void)=>{
    if(!regexPassword.test(value)){
      callback(new Error("Password must less than one character uppercase and one character special!"))
    }else{
      callback();
    }
  }

  const ValidatorRetypePassword=(rule:unknown,value:string,callback:(error?:Error)=>void)=>{
    if(value !== form.password){
      callback(new Error("retype password not valid!"))
    }else{
      callback();
    }
  }

  const ValidatorAge=(rule:unknown,value:number,callback:(error?:Error)=>void)=>{
    if(value < 18){
      callback(new Error("Age must more than 18 year old!"))
    }else{
      callback();
    }
  }

  const ValidatorStartTime=(rule:unknown,value:string,callback:(error?:Error)=>void)=>{
    const convertTimeDateStart = new Date(value).getTime();
    const today = getTimeToday();
    if(convertTimeDateStart < today){
      callback(new Error("Time start is not less than today"));
    }else{
      callback();
    }
  }

  const ValidatorEndTime=(rule:unknown,value:string,callback:(error?:Error)=>void)=>{
    const convertTimeDateEnd = new Date(value).getTime();
    const convertTimeDateStart = new Date(form.startTime).getTime();
    const today = getTimeToday();
    if(convertTimeDateEnd < today){
      callback(new Error("Time start is not less than today"));
    }else if(convertTimeDateEnd < convertTimeDateStart){
      callback(new Error("Time end is must more than start time"));
    }else{
      callback();
    }
  }

  const ValidatorFileAvatar=(rule:unknown,value:File,callback:(error?:Error)=>void)=>{

    const getSizeFile = value.size;

    if(getSizeFile > 1500){
      callback(new Error("File exceed size approve"));
    }else{
      callback();
    }
  }

  const getTimeToday = ()=>{
    const  today = new Date();
    return today.getTime();
  }

  const rules = reactive<FormRules<typeof form>>({
    username: [
      { required: true, message: 'Field username is not empty', trigger: 'blur' },
      { validator: ValidatorUsername, trigger: 'blur' }
    ],
    password: [
      { required: true, message: 'Field username is not empty', trigger: 'blur' },
      { validator: ValidatorPassword, trigger: 'blur' }
    ],
    retypePassword: [
      { required: true, message: 'Field username is not empty', trigger: 'blur' },
      { validator: ValidatorRetypePassword, trigger: 'blur' }
    ],
    age: [
      { required: true, message: 'Field username is not empty', trigger: 'blur' },
      { validator: ValidatorAge, trigger: 'blur' }
    ],
    startTime: [
      { required: true, message: 'Field username is not empty', trigger: 'blur' },
      { validator: ValidatorStartTime, trigger: 'blur' }
    ],
    endTime: [
      { required: true, message: 'Field username is not empty', trigger: 'blur' },
      { validator: ValidatorEndTime, trigger: 'blur' }
    ],
    avatar: [
      { required: true, message: 'Field avatar is not empty', trigger: 'blur' },
      { validator: ValidatorFileAvatar, trigger: 'blur' }
    ]
  });

  const handleSubmitForm = () => {
    formUser.value.validate((valid:boolean)=>{
       if(valid){
         console.log(form);
       }
    });
  }

  const handeChange =(file:File)=>{
      form.avatar = file.raw;
      const reader = new FileReader();
      reader.onload=(e)=>{
        form.imageUrl = e.target?.result as string;
      }
      reader.readAsDataURL(file.raw);
  }

  const handleCancel = () => {
    console.log(formUser.value);
  }

  const getDetailInfo = (value:any)=>{
    form.username = value.username;
    form.password = value.password;
    form.retypePassword = value.retypePassword;
    form.age = value.age;
    form.startTime = value.startTime;
    form.endTime = value.endTime;
    form.imageUrl = value.imageUrl;
  };

  const getSayHello =()=>{
    console.log("Hello world!");
  }

  defineExpose({
    isUpdate,getDetailInfo,getSayHello
  });

  watchEffect(()=>{
    console.log(isUpdate.value);
  })

</script>

<style scoped lang="scss">
  .register {
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .container {
    border: 1px solid #ddd;
    border-radius: 8px;
    overflow: hidden;
    padding: 20px;
    width: 700px;
  }

  .preview-image {
    max-width: 200px;
    max-height: 200px;
    border: 1px solid #ddd;
    border-radius: 4px;
  }

</style>
