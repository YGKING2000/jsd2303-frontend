Vue.component("my-header", {
    data() {
        return {
            user: JSON.parse(localStorage.getItem("user")),
            activeIndex: 0,
            keyword: ""
        };
    },
    methods: {
        search() {
            if (this.keyword.trim() === "") {
                this.$message({
                    type: "error",
                    message: "请输入内容后再搜索!"
                });
            }
            location.href = `/contentList.html?keyword=${this.keyword}`
        },
        logout() {
            if (confirm("你确认退出登录吗？")) {
                axios.get("/v1/users/logout").then(() => {
                    localStorage.clear();
                    location.href = "/";
                });
            }
        },
        handleSelect(key) {
            if (parseInt(key) === 0) {
                location.href = "/";
            } else {
                location.href = `/contentList.html?type=${key}`;
            }
        }
    },
    created() {

        this.activeIndex = new URLSearchParams(location.search).get("type");
        if (location.pathname === "/" || location.pathname === "/index.html") {
            this.activeIndex = "0";
        }
        if (location.pathname.includes("postArticle.html")) this.activeIndex = null;
    },
    template: `
      <el-header height="80px">
      <div class="center">
        <el-row gutter="20">
          <el-col span="6">
            <a href="/index.html">
              <img src="imgs/icon.png" width="196" height="65">
            </a>
          </el-col>
          <el-col span="10">
            <el-menu mode="horizontal" :default-active="activeIndex" @select="handleSelect" active-text-color="orange">
              <el-menu-item index="0">首页</el-menu-item>
              <el-menu-item index="1">食谱</el-menu-item>
              <el-menu-item index="2">视频</el-menu-item>
              <el-menu-item index="3">资讯</el-menu-item>
            </el-menu>
          </el-col>
          <el-col span="6">
            <el-input style="margin-top: 15px" v-model="keyword"
                      @keydown.native.enter="search()" placeholder="请输入搜索的关键字">
              <el-button slot="append" @click="search()" icon="el-icon-search"></el-button>
            </el-input>
          </el-col>
          <el-col span="2">
            <el-popover v-if="user === null" placement="top-start" title="欢迎来到烘焙坊!" width="200" trigger="hover">
              <div slot="reference">
                <i class="el-icon-user"
                   style="font-size: 30px;position: relative;top: 20px"></i>
              </div>
              <el-button type="info" @click="location.href='/reg.html'">注册</el-button>
              <el-button style="background-color: orange" @click="location.href='/login.html'">登录</el-button>
            </el-popover>
            <el-popover v-else placement="top-start" title="欢迎来到烘焙坊!" width="200" trigger="hover">
              <div slot="reference">
                <i class="el-icon-user"
                   style="font-size: 30px;position: relative;top: 20px"></i>
              </div>
              <div style="text-align:center;">
                <a href="/personal.html">
                  <el-avatar :src="user.imgUrl"></el-avatar>
                </a>
                <hr>
                <a href="/personal.html" style="text-decoration:none;">个人中心</a>
                <a href="javascript:void(0);" @click="logout()" style="text-decoration:none;">退出登录</a>
                <a href="/admin.html" style="text-decoration:none;" v-if="user.isAdmin">管理界面</a>
              </div>
            </el-popover>
          </el-col>
        </el-row>
      </div>
      </el-header>
    `
});