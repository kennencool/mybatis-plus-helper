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
        
        <el-main>Main</el-main>
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
    }
  },
  mounted() {
    
  },
  
  methods:{
    getTables(database){
      this.getRequest("/table/"+database).then(resp=>{
        if(resp){
          this.tables = resp;
        }
      })
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
