<template>
    <div>
        <div class="studentTestHeader">
            <el-input  v-model="paramsObj.idOrName" placeholder="请输入课程编号或名称..." size="small" style="width: 350px"></el-input>
            <el-button  @click="serchByIdOrName" size="small" icon="el-icon-search" type="primary">搜索</el-button>
        </div>
        <div>
            <el-tag style="margin-bottom: 10px">学生考试成绩</el-tag>
            <el-button   size="small" type="primary">学生考试成绩分析</el-button>
            <el-table
                    :data="studentTestInfos"
                    height="400"
                    border
                    style="width: 100%">
                <el-table-column
                        width="100"
                        prop="cid"
                        label="课程编号">
                </el-table-column>
                <el-table-column
                        width="180"
                        prop="cname"
                        label="课程姓名">
                </el-table-column>
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
                        width="100"
                        prop="kind"
                        label="考试次数">
                </el-table-column>
                <el-table-column
                        width="100"
                        prop="score"
                        label="学生得分">
                </el-table-column>
                <el-table-column
                        width="100"
                        prop="sumscore"
                        label="总分">
                </el-table-column>
                <el-table-column
                        width="100"
                        prop="classranking"
                        label="班级排名">
                </el-table-column>
                <el-table-column
                        width="100"
                        prop="graderanking"
                        label="年级排名">
                </el-table-column>
                <el-table-column
                        width="100"
                        prop="ratio"
                        label="占比">
                </el-table-column>
                <el-table-column label="操作" width="180" fixed="right">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                @click="handle(scope.row)">详情</el-button>
                    </template>
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
    </div>
</template>
<script>
    module.exports= {
        data(){
            return {
                dialogVisible : false,
                dialogVisible1 : false,
                dialogVisible2 : false,
                dialogVisible3 : false,
                total : 0,
                paramsObj:{
                    pageNum : 0,
                    pageSize : 8,
                    idOrName : '',
                    htid : 0,
                    stid : JSON.parse(window.sessionStorage.getItem("loginStudent")).stid,
                },
                studentTestInfos: [],
            }
        },
        methods:{
            currentChange(count){//分页回调
                this.paramsObj.pageNum=count;
                this.initstudentTestInfos();
            },
            serchByIdOrName() { // 点击搜索
                this.paramsObj.pageNum = 1;
                this.initstudentTestInfos();
            },
            initstudentTestInfos(){
                getRequest('http://localhost:8080/searchStudentTest',this.paramsObj).then(resp=>{
                    if (resp.status==200) {
                        this.studentTestInfos=resp.data.data;
                        this.total=resp.data.total;
                    }
                })
            },
        },
        mounted(){
            this.initstudentTestInfos()
        }
    }
</script>
<style scoped>
    .studentTestHeader{
        margin: 10px 0;
    }
    .studentTopic{
        margin: 10px 0;
    }
</style>