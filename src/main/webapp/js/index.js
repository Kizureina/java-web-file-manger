vm = new Vue({
    el:"#app",
    data() {
        return {
            editdate: '',
            filename: '',
            size: '',
            username:"",
            tableData:{},
            multipleSelection: [],
            fileList: []
        }
    },
    methods: {
        backFolder(){
            axios.get("http://localhost/backSuperFolderServlet")
                .then(resp => {
                    var obj = resp.data;
                    if (obj == null){
                        this.$message.error('已为根目录！');
                    }
                    this.tableData = Array(obj.length);

                    for (let i = 0; i < obj.length; i++){
                        let f = obj[i];
                        let rawDate = new Date(f.editTime);
                        let size = f.filesize > 0 ? f.filesize + "KB" : "-";
                        d = dateFormat("YYYY-mm-dd HH:MM", rawDate);
                        this.tableData.push({
                            name: f.fileName,
                            date: d,
                            size: size
                        })
                    }
                });
        },
        openFolder(){
            if (this.multipleSelection.length !== 1){
                alert("请只选择一个文件进行操作>_<");
                return;
            }
            let folderName = this.multipleSelection[0].name;
            axios.get("http://localhost/getSubFilesServlet?indexName=" + folderName)
                .then(resp => {
                    var obj = resp.data;
                    this.tableData = Array(obj.length);
                    for (let i = 0; i < obj.length; i++){
                        let f = obj[i];
                        let size = f.filesize > 0 ? f.filesize + "KB" : "-";
                        let rawDate = new Date(f.editTime);
                        d = dateFormat("YYYY-mm-dd HH:MM", rawDate);
                        this.tableData.push({
                            name: f.fileName,
                            date: d,
                            size: size
                        })
                    }
                });
        },
        renameFile(){
            if (this.multipleSelection.length !== 1){
                alert("请选择一个文件进行重命名>_<");
                return;
            }
            this.$prompt('请输入文件夹名', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消'
            }).then(({ value }) => {
                if (value.length > 64){
                    this.$message({
                        type: 'error',
                        message: '文件名过长'
                    });
                    return;
                }
                let oldName = this.multipleSelection[0].name;
                let url = "http://localhost/renameFileServlet?oldName=" + oldName + "&newName=" + value;
                console.log(encodeURI(url));
                axios.get(encodeURI(url))
                    .then(resp=>{
                        if (resp.data === true){
                            this.$message({
                                type: 'success',
                                message: '成功重命名为: ' + value
                            });
                        }
                    })
                    .catch(error=>{
                        console.log(error);
                        this.$message.error('出错了!请检查文件名！');
                    })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消输入'
                });
            });
        },
        deleteConfirm() {
            if (this.multipleSelection.length < 1){
                alert("请选择文件>_<");
                return;
            }
            this.$confirm('此操作将永久删除该文件, 是否继续?', '警告', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.deleteFile();
            }).catch(e => {
                console.log(e);
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        deleteFile(){
            for(let i = 0; i < this.multipleSelection.length; i++) {
                let url = "http://localhost/deleteFileServlet?indexName=" + this.multipleSelection[i].name;
                axios.get(encodeURI(url))
                    .then(resp => {
                        // console.log(resp.data);
                        if(resp.data === true){
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                        }else {
                            this.$message.error('删除失败！不可删除非空文件夹！');
                        }
                    })
                    .catch(error => {
                        console.log(error);
                        this.deleteFlag = false;
                    })
            }
        },
        toggleSelection(rows) {
            if (rows) {
                rows.forEach(row => {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        handleCommand(a){
            axios.get("http://localhost/destroySessionServlet")
                .then(resp => {
                    alert("注销成功！");
                    window.location.href='/login.html';
                })
                .catch(e =>{
                    alert("注销失败!请重试！");
                });
        },
        getFileInfo(){
            axios.get("http://localhost/fileInfoServlet")
                .then(resp=>{
                    console.log(resp.data);
                    var obj = resp.data;
                    this.tableData = Array(obj.length);
                    for (let i = 0; i < obj.length; i++){
                        let f = obj[i];
                        let size = f.filesize > 0 ? f.filesize + "KB" : "-";
                        let rawDate = new Date(f.editTime);
                        d = dateFormat("YYYY-mm-dd HH:MM", rawDate);
                        this.tableData.push({
                            name: f.fileName,
                            date: d,
                            size: size
                        })
                    }
                });
        },

        getUserName() {
            axios.get("http://localhost/userInfo")
                //then获取成功；response成功后的返回值（对象）
                .then(response=>{
                    this.username = response.data;
                })
                //获取失败
                .catch(error=>{
                    console.log(error);
                    alert('网络错误!');
                })
        },
        open() {
            this.$prompt('请输入文件夹名', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消'
                // inputPattern: /\\*\/\\*/,
                // inputErrorMessage: '文件名格式不正确'
            }).then(({ value }) => {
                if (value.length > 64){
                    this.$message({
                        type: 'error',
                        message: '文件名过长'
                    });
                    return;
                }
                let url = "http://localhost/createIndexServlet?indexName=" + value;
                // console.log(encodeURI(url));
                axios.get(encodeURI(url))
                    .then(resp=>{
                        this.$message({
                            type: 'success',
                            message: '新建文件夹名为: ' + value
                        });
                    })
                    .catch(error=>{
                        console.log(error);
                        this.$message.error('出错了!请检查文件名！');
                    })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消输入'
                });
            });
        },
        upload() {
            this.$alert('<template><el-upload\n' +
                '  class="upload-demo"\n' +
                '  drag\n' +
                '  action="https://jsonplaceholder.typicode.com/posts/"\n' +
                '  multiple>\n' +
                '  <i class="el-icon-upload"></i>\n' +
                '  <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>\n' +
                '  <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>\n' +
                '</el-upload></template>', 'HTML 片段', {
                dangerouslyUseHTMLString: true
            })
        }
    },
    mounted(){
        this.getUserName();
        this.getFileInfo();
    }
});
function dateFormat(fmt, date) {
    let ret;
    const opt = {
        "Y+": date.getFullYear().toString(),        // 年
        "m+": (date.getMonth() + 1).toString(),     // 月
        "d+": date.getDate().toString(),            // 日
        "H+": date.getHours().toString(),           // 时
        "M+": date.getMinutes().toString(),         // 分
        "S+": date.getSeconds().toString()          // 秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
            fmt = fmt.replace(ret[1], (ret[1].length === 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        }
    }
    return fmt;
}