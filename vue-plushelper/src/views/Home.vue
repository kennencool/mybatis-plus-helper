<template>
  <div class="homeBox">
    <el-container>
      <el-header class="homeHeader">
        <div style="font-size:400%">
          <i class="el-icon-s-platform" ></i>
        </div>
        <el-button icon="el-icon-search" size="small" @click="showAddDatabase">连接数据库</el-button>
      </el-header>
      
      <el-container>
        <el-aside width="250px">
          <el-menu>
            <el-submenu :index="index+''" v-for="(item,index) in databases" :key="index">
              <template #title>
                <i class="el-icon-s-platform" style="color: aqua; margin-right: 5px"></i>
                <span>
                  {{item}}<el-button style="margin-left: 20px" @click="getTables(item)" size="mini" >打开</el-button>
                </span>
              </template>
              <el-menu-item-group>
                <el-menu-item :index="tableItem+''" v-for="(tableItem,tableIndex) in tables" :key="tableIndex">
                  <i class="el-icon-tickets" style="color: aqua; margin-right: 5px"></i><span>{{tableItem}}</span>
                </el-menu-item>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-aside>
        
        <el-main>
          <el-form :model="GeneratorParam" :rules="ruleForGenerate" ref="ruleForm" label-width="240px" class="demo-ruleForm">
            <el-form-item label="项目根目录路径:" prop="projectPath">
              <el-input  placeholder="C:/my_github/mybatis-plus-helper/springboot-plushelper" v-model="GeneratorParam.projectPath" style="width: 400px"></el-input>
            </el-form-item>
            <el-form-item label="项目包结构:" prop="packagePath">
              <el-input  placeholder="com.example.demo" v-model="GeneratorParam.packagePath" style="width: 220px"></el-input>
            </el-form-item>
            <el-form-item label="数据库名:" prop="database">
              <el-tag>{{GeneratorParam.database}}</el-tag>
            </el-form-item>
            <el-form-item label="数据库表名:" prop="tables">
              <template>
                <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
                <div style="margin: 15px 0;"></div>
                <el-checkbox-group v-model="checkedTables" @change="handleCheckedTablesChange">
                  <el-checkbox v-for="table in tables" :label="table" :key="table">{{table}}</el-checkbox>
                </el-checkbox-group>
              </template>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitGenerate('ruleForm')">生成代码</el-button>
              <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-main>
      </el-container>
    </el-container>
  
  <div>
      <el-dialog
          title="编辑MySQL数据库连接"
          :visible.sync="dialogVisible"
          width="40%">
        <div>
          <el-form ref="connectionForm" :model="connectionParam" :rules="rules" >
            <el-col>
              <el-row>
                <el-form-item label="主机名或ip地址:" prop="host">
                  <el-input v-model="connectionParam.host" size="small" style="width: 300px"
                            prefix-icon="el-icon-edit" placeholder="127.0.0.1"></el-input>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item label="端口号:" prop="port">
                  <el-input v-model="connectionParam.port" size="small" style="width: 300px"
                            prefix-icon="el-icon-edit" placeholder="3306"></el-input>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item label="用户名:" prop="user">
                  <el-input v-model="connectionParam.user" size="small" style="width: 300px"
                            prefix-icon="el-icon-edit" placeholder="root"></el-input>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item label="密码:" prop="password">
                  <el-input v-model="connectionParam.password" size="small" style="width: 300px"
                            prefix-icon="el-icon-edit" placeholder="请输入密码"></el-input>
                </el-form-item>
              </el-row>
              <el-row>
                <el-form-item label="数据库名:" prop="database">
                  <el-input v-model="connectionParam.database" size="small" style="width: 300px"
                            prefix-icon="el-icon-edit" placeholder="请输入数据库名"></el-input>
                </el-form-item>
              </el-row>
            </el-col>
          </el-form>
        </div>
        <template>
          <span>
            <el-button size="mini" @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" size="mini" @click="doAddDatabase">测试连接</el-button>
          </span>
        </template>
      </el-dialog>
  </div>
  </div>
</template>

<script>

export default {
  name: "Home",
  data(){
    return{
      databases: [],
      tables:[],
      dialogVisible:false,
      connectionParam: {
        host: '127.0.0.1',
        port: '3306',
        user: 'root',
        password: 'nbuser',
        database: ''
      },
      rules:{
        host: [{required:true, message:'请输入主机名或ip', trigger:'blur'}],
        port: [{required:true, message:'请输入端口号', trigger:'blur'}],
        user: [{required:true, message:'请输入用户名', trigger:'blur'}],
        password: [{required:true, message:'请输入密码', trigger:'blur'}],
        database: [{required:true, message:'请选择数据库', trigger:'blur'}],
      },
      ruleForGenerate:{
        projectPath: [{required:true, message:'请输入项目路径', trigger:'blur'}],
        packagePath: [{required:true, message:'请输入项目包结构', trigger:'blur'}],
      },
      GeneratorParam:{
        projectPath: '',
        database: '', 
        packagePath: '',
        tables: '',
      },
      checkAll: false,
      isIndeterminate: true,
      checkedTables: [],
    }
  },
  methods:{
    getTables(database){
      this.getRequest("/table/"+database).then(resp=>{
        if(resp){
          this.tables = resp;
        }
      });
      this.GeneratorParam.database = database;
    },
    doAddDatabase(){
      this.postRequest("/database/",this.connectionParam).then(resp=>{
        if(resp){
          this.dialogVisible = false;
          this.databases.push(this.connectionParam.database)
        }
      });
    },
    showAddDatabase(){
      this.dialogVisible = true;
    },
    submitGenerate(){
      this.GeneratorParam.tables = this.checkedTables[0];
      for(let i=0; i<this.checkedTables.length; i++){
        this.GeneratorParam.tables += "," + this.checkedTables[i];
      }
      this.postRequest("/generator/",this.GeneratorParam).then(resp=>{
        if(resp){
        }
      });
    },
    resetForm(formName){
      this.$refs[formName].resetFields();
    },
    handleCheckAllChange(val) {
      this.checkedTables = val ? this.tables : [];
      this.isIndeterminate = false;
    },
    handleCheckedTablesChange(value) {
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.tables.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.tables.length;
    }
  },
}
</script>

<style>
.homeHeader{
  background-color: #cac6c6;
  padding: 0px 15px;
  box-sizing: border-box;
  display: flex;
  align-items: center;
}
</style>
