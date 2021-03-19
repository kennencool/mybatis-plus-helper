<template>
  <div class="homeBox">
    <el-container>
      <el-header class="homeHeader">
        <div style="font-size:400%">
          <i class="el-icon-s-platform" ></i>
        </div>
        <el-button icon="el-icon-search" size="small">连接数据库</el-button>
      </el-header>
      
      <el-container>
        <el-aside width="200px">
          <el-menu>
            <el-submenu @click="getTables(item)" :index="index+''" v-for="(item,index) in databases" v-if="!item.hidden" :key="index">
              <template #title>
                <i class="el-icon-s-platform" style="color: aqua; margin-right: 5px"></i><span>{{item}}</span>
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
  </div>
</template>

<script>

export default {
  name: "Home",
  data(){
    return{
      databases: ['vhr','bbb','ccc'],
      tables:[111,222,333],
      
    }
  },
  mounted() {
    
  },
  
  methods:{
    getTables(database){
      console.log(database);
      this.getRequest("/table/"+database).then(resp=>{
        if(resp){
          this.tables = resp.data;
        }
      })
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
