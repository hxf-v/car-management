<template>
  <div class="container" ref="layoutCon">
    <Layout class="header">
      <Header>
        <Menu mode="horizontal" theme="dark">
          <div class="layout-logo">
            <img style="width:50px;height:50px" src="../assets/AHU.png" alt="">
            <span class="header-title">安徽大学车辆管理系统</span>
          </div>
          <div class="layout-nav">
            <span class="header-right-item">
              合肥 晴 40℃
            </span>
            <span class="header-right-item">
              WangXingWen
            </span>
            <span class="header-right-item">
              <Icon type="md-book" />
              使用文档
            </span>
          </div>
        </Menu>
      </Header>
    </Layout>
    <Layout class="sider">
      <Sider hide-trigger :style="{background: '#fff'}">
        <Menu @on-select="selectMenu" theme="light" width="auto" :active-name="activeSide" :open-names="['1', '2', '3']">
          <Submenu name="1">
            <template slot="title">
              <Icon type="ios-navigate"></Icon>
              首页
            </template>
            <MenuItem name="1-1" to="/home/carTravel">校内车辆轨迹</MenuItem>
            <MenuItem name="1-2" to="/home/carInfo">校内车辆信息</MenuItem>
          </Submenu>
          <Submenu name="2">
            <template slot="title">
              <Icon type="ios-keypad"></Icon>
              停车管理
            </template>
            <MenuItem name="2-1" to="/home/carStop">校园车辆停车管理</MenuItem>
          </Submenu>
          <Submenu name="3">
            <template slot="title">
              <Icon type="ios-analytics"></Icon>
              车辆注册管理
            </template>
            <MenuItem name="3-1" to="/home/carRegister">校园车辆注册</MenuItem>
          </Submenu>
        </Menu>
      </Sider>
    </Layout>
    <Layout class="content" :style="{padding: '14px'}">
      <Content :style="{background: '#fff'}">
        <div :style="{padding: '14px', background: '#fff'}">
          <router-view></router-view>
        </div>
      </Content>
    </Layout>
  </div>
</template>
<script>
import storage from 'good-storage'
import { mapActions } from 'vuex'
export default {
  data() {
    return {
      containerHeight: ''
    }
  },
  mounted() {
    this.getHeight()
    window.addEventListener('resize', this.getHeight)
    this.$api.getSoftware().then(res => {
      console.log(res)
    })
  },
  methods: {
    selectMenu(num) {
      storage.set('active', num)
    },
    // vuex 设置所有组件中table表单的高度
    getHeight () {
      let height = this.$refs.layoutCon ? this.$refs.layoutCon.clientHeight : ''
      this.setTableHeight(height - 200)
    },
    ...mapActions([
      'setTableHeight'
    ])
  },
  computed: {
    activeSide () {
      return storage.get('active', '1-1')
    }
  }
}
</script>
<style scoped>
.container {
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
  overflow: hidden;
}
.header {
  position: absolute;
  left: 0;
  top: 0;
  right: 0;
  height: 65px;
}
.sider {
  position: absolute;
  left: 0;
  top: 65px;
  right: 0;
  bottom: 0;
  overflow: auto;
  width: 200px;
}
.content {
  position: absolute;
  left: 200px;
  top: 65px;
  right: 0;
  bottom: 0;
  overflow: auto;
}
.layout-logo {
  width: 250px;
  height: 50px;
  float: left;
  position: relative;
  top: 6px;
}
.header-title {
  color: #d7dde4;
  position: relative;
  bottom: 20px;
  font-size: 15px;
  font-weight: bold;
  left: 10px;
}
.layout-nav {
  float: right;
  color: #d7dde4;
  width: 500px;
  margin: 0 auto;
  display: flex;
  justify-content: flex-end
}
.header-right-item {
  margin-right: 15px
}
</style>
