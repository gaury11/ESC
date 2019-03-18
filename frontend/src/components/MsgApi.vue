<template>
  <div class="main">
    <v-container grid-list-md>
    <h1>1. Target URL선택</h1>
    <v-layout wrap >
        <v-flex xs8>
            <v-select
                label="Target URL 선택"
                v-model="apiTargetInfo"
                :hint="`${apiTargetInfo.targetName} = ${apiTargetInfo.targetUrl}`"
                :items="apiTargetInfos"
                item-text="targetName"
                item-value="targeUrl"
                persistent-hint
                return-object
                single-line
            ></v-select>
        </v-flex>
        <v-flex xs6>
            <v-text-field 
            v-model="editedTargetName"
            :counter="50"
            label="Name"
            ></v-text-field>
        </v-flex>
        <v-flex xs6>
            <v-text-field 
            v-model="editedTargetUrl"
            :counter="50"
            label="Url"
            ></v-text-field>
        </v-flex>
        <v-flex xs6>
            <v-btn color="orange lighten-1" @click="saveTargetInfo" dark>Save</v-btn>
        </v-flex>
        <v-flex xs12>
            <h3>요청 URL = {{apiTargetInfo.targetUrl}} </h3>
        </v-flex>
    </v-layout>
    </v-container>
    <v-container grid-list-md>
        <h1>2. Request - API 선택</h1>
        <v-layout wrap>            
            <v-flex xs8>
                <v-select
                    label="API 선택"
                    v-model="reqApi"
                    :items="reqApiLists"
                    item-text="key"
                    persistent-hint
                    return-object
                    single-line
                    @change="onChange($event)"
                ></v-select>
            </v-flex>
            <v-flex xs12>
                <h3>요청 URL = {{apiTargetInfo.targetUrl}}{{reqApi.url}} </h3>
            </v-flex>
            <v-flex xs2>
                <v-btn color="orange lighten-1" v-on:click="requestMsgApi" dark>요청</v-btn>
            </v-flex>
            <v-flex xs12>
                <h3>d=</h3>
            </v-flex>    
            <v-flex xs12>
                <p>
                    <tree-view :data="requestParam" :options="{modifiable: true}" max-depth="5"></tree-view>
                </p>
            </v-flex>           
        </v-layout>
    </v-container>
    <v-container grid-list-md>
        <h1>3. Response - 결과</h1>
        <v-layout wrap>
            <v-flex xs10>
                <h3> 결과 = {{responseResult}}</h3>
            </v-flex>
            <v-flex xs12>
                <p>
                    <tree-view :data="responseData" :options="{modifiable: false}" max-depth="5"></tree-view>
                </p>
            </v-flex> 
            <!--
            <v-flex xs8>
                <v-textarea
                outline
                name="input-7-4"
                label="RESPONSE"
                auto-grow
                v-model="result"
                ></v-textarea>
            </v-flex>
            -->
        </v-layout>
    </v-container>
  </div>
</template>
<script>

export default {
  name: 'Main',
  data () {
    return {
      title1: 'Request',
      title2: 'Response',
      apiTargetInfos: [],
      apiTargetInfo: { targetName: '타겟 Url', targetUrl: 'localhost:8081/msg-api'},
      editedTargetName: '',
      editedTargetUrl: '',
      requestData: null,
      reqApiLists: [
          {key: 'ping', url: '/ping'},
          {key: 'checkDB', url: '/checkDB'},
          {key: 'deviceCert', url: '/deviceCert.m'
          , param: {
                "appKey": "android", 
                "uuid": "msg_api_test",
                "pushToken": "test_token",
                "appVer": "10.0",
                "os": "A",
                "osVer": "8.0",
                "device": "SM_NOTE8",
                "custId": "soon",
                "sessCnt": "1",
                "sdkVer" : "1.0.1",
                "userData": {
                    "custName": "soon",
                    "birthday": "",
                    "phoneNumber": "",
                    "location1": "",
                    "location2": "",
                    "gender": "",
                    "data1": "",
                    "data2": "",
                    "data3": "" 
                }
          }},
          {key: 'loginPms', url: '/loginPms.m'},
          {key: 'setConfig', url: '/setConfig.m'},
          {key: 'newMsg', url: '/newMsg.m'},
          {key: 'readMsg.m', url: '/readMsg.m'}
      ],
      reqApi: {name: null, url: null, param: null},
      requestApiUrl: '',
      requestParam: {},
      requestParam_deviceCert: {
        "appKey": "android", 
        "uuid": "msg_api_test",
        "pushToken": "test_token",
        "appVer": "10.0",
        "os": "A",
        "osVer": "8.0",
        "device": "SM_NOTE8",
        "custId": "soon",
        "sessCnt": "1",
        "sdkVer" : "1.0.1",
        "userData": {
            "custName": "soon",
            "birthday": "",
            "phoneNumber": "",
            "location1": "",
            "location2": "",
            "gender": "",
            "data1": "",
            "data2": "",
            "data3": "" 
        }
      },
      responseResult: "",
      responseData: {},
      conn: {
          appKey: null,
          url: null,
          port: null,
          context: null
      },
      result: null,
      fcmItem:{
          serverKey: null,
          pushToken: null
      },
      apnsItem:{
          certName: null,
          pushToken: null
      }
    }
  },
  created: function(){
    this.getMsgApi()
  },
  methods: {
    saveTargetInfo(){

        const saveTargetName = this.editedTargetName
        const saveTargetUrl = this.editedTargetUrl
        console.log("저장 데이터 = name : " + saveTargetName + " / url : " + saveTargetUrl)

        this.axios
            .post(this.$API_URL+"/apiTarget/save", {
                headers: {
                    "Content-Type": "application/json"
                },
                targetName: saveTargetName,
                targetUrl: saveTargetUrl
            }).then(response => {
                console.log("saveTargetInfo - response : " + response.data)
                this.getMsgApi()
            }).catch(error => {
                if (error.response) {
                    // The request was made and the server responded with a status code
                    // that falls out of the range of 2xx
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                } else if (error.request) {
                    // The request was made but no response was received
                    // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
                    // http.ClientRequest in node.js
                    console.log(error.request);
                } else {
                    // Something happened in setting up the request that triggered an Error
                    console.log('Error', error.message);
                }
                console.log(error.config);
            })
        
    },    

    getMsgApi: function(){
        console.log("getMsgApi")
        this.axios
            .get(this.$API_URL+'/apiTarget')
            .then(response => {
                console.log(response.data)
                this.requestData = response.data
                this.apiTargetInfos = response.data
            })
    },
    onChange(event){
        console.log("event : " + event.param)
        this.requestApiUrl = event.url
        this.requestParam = event.param
        
    },
    requestMsgApi(){

        this.responseData = {};

        const reqUrl = this.apiTargetInfo.targetUrl
        const reqApiUrl = this.requestApiUrl
        const reqParam = this.requestParam

        console.log("reqUrl : " + reqUrl + " / reqApiUrl : " + reqApiUrl + " / reqParam : " + reqParam)

        if(reqParam == null){
            console.log("not exist param")

            this.axios
                .get(reqUrl + reqApiUrl)
                .then(response => {
                    console.log(response.data)
                    this.responseResult = "요청 성공"
                    this.responseData = response.data
                }).catch(error => {
                if (error.response) {
                    // The request was made and the server responded with a status code
                    // that falls out of the range of 2xx
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                } else if (error.request) {
                    // The request was made but no response was received
                    // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
                    // http.ClientRequest in node.js
                    console.log(error.request);
                } else {
                    // Something happened in setting up the request that triggered an Error
                    console.log('Error', error.message);
                }
                console.log(error.config);
                this.responseResult = error.message
            })

        }else if(reqParam != null){ 
            console.log("exist param")

            console.log("reqParam : " + JSON.stringify(reqParam));
            const d = reqParam
            this.axios
                .post(reqUrl + reqApiUrl, {
                    headers: {
                        "Content-Type": "application/x-www-form-urlencoded"
                    },
                    d: reqParam
                })
                .then(response => {
                    console.log(response.data)
                    this.responseResult = "요청 성공"
                    this.responseData = response.data
                }).catch(error => {
                if (error.response) {
                    // The request was made and the server responded with a status code
                    // that falls out of the range of 2xx
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                } else if (error.request) {
                    // The request was made but no response was received
                    // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
                    // http.ClientRequest in node.js
                    console.log(error.request);
                } else {
                    // Something happened in setting up the request that triggered an Error
                    console.log('Error', error.message);
                }
                console.log(error.config);
                this.responseResult = error.message
            })
        }

    },
    clickBtn: function(){
        this.axios
            //.get('https://api.myjson.com/bins/74l63')
            .get('http://localhost:8081/codeInfo/list')
            .then(response => {
                console.log(response.data)
            })
    },
    sendMsgApi: function(){

        const jsonData = JSON.stringify({
            targetName: 'test_name',
            targetUrl: 'test_url'
        });

        console.log('url : ' +  this.apiTargetInfo.targetUrl + 'ping')
        this.axios
            //.get('http://localhost:9090/api/ping')
            .post('http://localhost:9090/apiTarget/save', {
                headers: {
                    'Content-Type': 'application/json' 
                },
                targetName: 'test_name',
                targetUrl: 'test_url'
            })
            //.get(this.apiTargetInfo.targetUrl + 'ping')
            .then(response => {
                console.log(response.data)
                this.result = response.data
            })
            .catch(error => {
                if (error.response) {
                    // The request was made and the server responded with a status code
                    // that falls out of the range of 2xx
                    console.log(error.response.data);
                    console.log(error.response.status);
                    console.log(error.response.headers);
                } else if (error.request) {
                    // The request was made but no response was received
                    // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
                    // http.ClientRequest in node.js
                    console.log(error.request);
                } else {
                    // Something happened in setting up the request that triggered an Error
                    console.log('Error', error.message);
                }
                console.log(error.config);
            })
    }
  }
}
</script>
