<template>
  <div class="container">
    <form @submit="handleSubmit" class="container__submit">
      <div class="container__item">
        <div class="username" for="username" >Username</div>
        <input type="text" id="username" v-model="dataForm.username">
        <div class="error-message" :class="{show:errorValidate.username}" v-show="errorValidate.username">{{errorValidate.username}}</div>
      </div>
      <div class="container__item">
        <div class="password" for="password">Password</div>
        <input type="password" id="password" v-model="dataForm.password">
        <div class="error-message" :class="{show:errorValidate.password}" v-show="errorValidate.password">{{errorValidate.password}}</div>
      </div>
      <div class="container__item">
        <div class="retype-password" for="retype-password">Retype Password</div>
        <input type="password" id="retype-password" v-model="dataForm.retypePassword">
        <div class="error-message" :class="{show:errorValidate.retypePassword}" v-show="errorValidate.retypePassword">{{errorValidate.retypePassword}}</div>
      </div>
      <div class="container__item">
        <div class="age" for="age">Age</div>
        <input type="number" id="age" v-model="dataForm.age">
        <div class="error-message" :class="{show:errorValidate.age}" v-show="errorValidate.age">{{errorValidate.age}}</div>
      </div>
      <div class="container__item">
        <div class="start-time" for="start-time">StartTime</div>
        <input type="date" id="start-time" v-model="dataForm.startTime">
      </div>
      <div class="container__item">
        <div class="end-time" for="end-time">EndTime</div>
        <input type="date" id="end-time" v-model="dataForm.endTime">
      </div>
      <div style="display: flex;align-items: center;justify-content: center;gap: 20px">
        <button type="submit">Submit Form</button>
        <button @click="handleCancel">Cancel</button>
      </div>
    </form>
  </div>

</template>

<script lang="ts" setup>

  import { reactive } from 'vue'

  const dataForm = reactive({
     username:"",
     password:"",
     retypePassword:"",
     age:0,
     startTime:null,
     endTime:null
  });

  const errorValidate = reactive({
    username:"",
    password:"",
    retypePassword:"",
    age:"",
    startTime:"",
    endTime:""
  })

  const handleCancel=()=>{

  }

  const handleSubmit=(e:Event)=>{
    e.preventDefault();
    if(!validateField()){
      console.log("Error validate!")
      return;
    }

    console.log(dataForm);
  }

  const validateField = ()=>{
    if(! validateFieldEmptyOrNull(dataForm.username) ||
        validateFieldEmptyOrNull(dataForm.password) ||
        validateFieldEmptyOrNull(dataForm.retypePassword) ||
        validateFieldEmptyOrNull(dataForm.age)){
      errorValidate.username = "Please fill data to field.Because fill require";
      return false;
    }

    if(!(validateUserName(dataForm.username) || validatePassword(dataForm.password,dataForm.retypePassword) || validateAge(dataForm.age))){
      return;
    }
    return true;
  }

  const validateUserName =(username:string)=>{
    if(username.length<6 && (username===username.toLowerCase())){
      errorValidate.username = "Username must more than six character and less than one character uppercase";
      return false;
    }
    return true;
  }

  const validatePassword=(password:string,retypePassword:string)=>{
    if(password!==retypePassword){
      errorValidate.retypePassword = "Password and retypePassword must equals";
      return false;
    }
    return true;
  }

  const validateAge=(age:number)=>{
    if(age<18){
      errorValidate.age = "Age must more than 18 year old";
      return false;
    }
    return true;
  }

  // const validateTime =(startTime:any,endTime:any)=>{
  //    const today = new Date();
  //
  //    today.setHours(0,0,0,0);
  //
  //
  // }

  const validateFieldEmptyOrNull = (field:any)=>{
    if(field===null || field===''){
      return false;
    }
    return true;
  }

</script>

<style lang="scss" scoped>
.container {
  max-width: 800px;
  margin: 0 auto;
  border: 1px solid #ddd;
  padding: 20px;
  &__item{
    display: flex;
    align-items: center;
    margin-bottom: 20px;
    div{
      width: 150px;
    }
    input{
      flex: 1;
    }
  }

  .error-message{
    font-size: 14px;
    color: red;
    line-height: 1;
    letter-spacing:0.5px;
    position: absolute;
    top: 100%;
    white-space: nowrap;
  }

  &__submit{
    .container__item:nth-child(1),.container__item:nth-child(2),.container__item:nth-child(3),.container__item:nth-child(4){
      position: relative;
    }
  }
}


</style>