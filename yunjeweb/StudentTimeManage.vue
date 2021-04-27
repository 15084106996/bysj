<template>
    <div>
        <el-tag style="margin-bottom: 10px">学生在线时长</el-tag>
        <div>
            <el-form label-width="80px">
                <el-form-item label="总时间">
                <el-col :span="2">
                    <el-input v-model="sum.sumtime" reasonly></el-input>
                </el-col>
                </el-form-item>
            </el-form>
        </div>
        <el-table
                :data="studentTimeInfos"
                height="250"
                border
                style="width: 100%">
            <el-table-column
                    width="100"
                    prop="stid"
                    label="学生学号">
            </el-table-column>
            <el-table-column
                    width="100"
                    prop="stname"
                    label="学生姓名">
            </el-table-column>
            <el-table-column
                    width="180"
                    prop="logintime"
                    label="登录">
            </el-table-column>
            <el-table-column
                    width="180"
                    prop="logouttime"
                    label="退出时间">
            </el-table-column>
            <el-table-column
                    width="100"
                    prop="onlinetime"
                    label="在线时长">
            </el-table-column>
        </el-table>
        <el-pagination
                background
                @current-change="currentChange"
                :current-page="paramsObj.pageNum"
                :page-size="paramsObj.pageSize"
                layout="prev, pager, next"
                :total="total">
        </el-pagination>
    </div>
</template>
<script>
    module.exports= {
        data(){
            return {
                sum : {
                    sumtime :''
                },
                total : 0,
                paramsObj:{
                    pageNum : 1,
                    pageSize : 1,
                    idOrName : '',
                    stid : JSON.parse(window.sessionStorage.getItem("loginStudent")).stid,
                },
                studentTimeInfos: [],//教师信息
            }
        },
        methods:{
            currentChange(count){//分页回调
                this.paramsObj.pageNum=count;
                this.initstudentTimeInfos();
            },
            initstudentTimeInfos(){
                getRequest('http://localhost:8080/searchStudentTime',this.paramsObj).then(resp=>{
                    if (resp.status==200) {
                        this.studentTimeInfos=resp.data.data;
                        this.total=resp.data.total;
                        this.sum.sumtime=resp.data.sum;
                    }
                })
            }
        },
        mounted(){
            this.initstudentTimeInfos();
        }
    }
</script>
