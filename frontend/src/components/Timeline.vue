<template>
  <div class="main">
  <v-container grid-list-md>
  <v-flex xs12>
    <v-toolbar dense floating>
        <!--
        <v-text-field
            hide-details
            prepend-icon="search"
            single-line
        ></v-text-field>
        --> 
    
      <v-dialog v-model="dialog" max-width="500px">
        <v-btn icon slot="activator">
          <v-icon>add_circle_outline</v-icon>
        </v-btn>
        <v-card>
          <v-card-title>
            <span class="headline">타임라인</span>
          </v-card-title>
          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>                
                <v-flex xs12>
                  <v-text-field v-model="editedItem.title" label="제목" hint="타임라인 제목"></v-text-field>
                </v-flex>
                <v-flex xs12>
                <v-flex xs12 sm6>
                  <v-select v-model="editedItem.timelineType"
                    :items="timelineType"
                    item-text="name"
                    item-value="value"
                    label="타입"
                    required
                  ></v-select>
                </v-flex>
                  <v-textarea                    
                    name="input-7-4"
                    label="컨텐츠"
                    v-model="editedItem.content"
                  ></v-textarea>                  
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="orange lighten-1" @click="close" flat >Cancel</v-btn>
            <v-btn color="orange lighten-1" @click="save" flat >Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-btn icon>
        <v-icon>more_vert</v-icon>
      </v-btn>
    </v-toolbar>
  </v-flex>
  <v-flex xs12>
  <v-timeline align-center>
    <v-timeline-item
      v-for="(item, i) in items"
      :key="i"
      :color="setColor(item.timelineType)"
      :icon="setIcon(item.timelineType)"
      fill-dot
    >
      <template slot="opposite">
        <span>{{item.regDate}}</span>
      </template>
      
      
      <v-card
        :color="setColor(item.timelineType)"
        dark
      >
        <v-card-title class="title">
          {{item.title}} 
          <v-btn icon small flat @click="deleteTimeline(item)">
              <v-icon >delete_outline</v-icon>
          </v-btn>
        </v-card-title>
        <v-card-text class="white text--primary">
          <p v-html="item.content"></p>
          <!--
          <v-btn
            :color="item.color"
            class="mx-0"
            outline
          >
            Button
          </v-btn>
          -->
        </v-card-text>
      </v-card>
      
    </v-timeline-item>
  </v-timeline>
  </v-flex>
  </v-container>
  </div>
</template>
<script>
  export default {
    data: () => ({
      dialog: false,
      timelineType: [
          {name: "공지", value: "notice"},
          {name: "이벤트", value: "event"},
          {name: "자유", value: "free"},
          {name: "테스트", value: "test"}
      ],
      editedItem: {},
      defaultItem: {
          title: null,
          type: null,
          content: null
      },
      items: []
      /*
      items: [
        {
          title: 'title1',
          content: 'content1',
          color: 'red lighten-2',
          icon: 'mdi-star'
        },
        {
          title: 'title2',
          content: 'content2',
          color: 'purple darken-1',
          icon: 'mdi-book-variant'
        },
        {
          title: 'title3',
          content: 'content3',
          color: 'green lighten-1',
          icon: 'mdi-airballoon'
        },
        {
          title: 'title4',
          content: 'content4',
          color: 'indigo',
          icon: 'mdi-buffer'
        }
      ]
      */
    }),

    created: function(){
        this.getTimelineList()
    },

    watch: {
        dialog (val){
            val || this.close()
        }
    },

    methods: {
        close(){
            this.dialog = false
        },
        save(){
            const saveTitle = this.editedItem.title
            const saveTimelineType = this.editedItem.timelineType
            const saveContent = this.editedItem.content.replace(/(?:\r\n|\r|\n)/g, '<br>')

            console.log("title : " + saveTitle +  " / timelineType : " + saveTimelineType + " / content : " + saveContent)

            this.axios
                .post(this.$API_URL+'/timeline/save', {
                    headers: {
                        "Content-Type": "application/json"
                    },
                    title: saveTitle,
                    timelineType: saveTimelineType,
                    content: saveContent
                })
                .then(response => {
                    console.log(response.data)
                    this.editedItem = this.defaultItem
                    this.close()
                    this.getTimelineList()
                })
        },
        deleteTimeline(item){
            console.log("deleteTimeline : " + item.idx)

            const idx = item.idx

            this.axios
                .delete(this.$API_URL+'/timeline/'+idx)
                .then(response => {
                    console.log(response.data)
                    this.getTimelineList()
                })

        },
        getTimelineList(){
            this.axios
                .get(this.$API_URL+'/timeline')
                .then(response => {
                    console.log(response.data)
                    this.items = response.data
                })
        },
        setColor(i){
            var color = "indigo"
            switch(i){
                case "notice":
                    color = "purple darken-1"
                    break
                case "event":
                    color = "green lighten-1"
                    break
                case "free":
                    color = "indigo"
                    break   
                default:
                    color = "red lighten-2"
                    break                     
            }
            return color
        },        
        setIcon(i){
            var icon = "mdi-airballoon"
            switch(i){
                case "notice":
                    icon = "notifications"
                    break
                case "event":
                    icon = "event"
                    break
                case "free":
                    icon = "wb_sunny"
                    break   
                default:
                    icon = "help_outline"
                    break                     
            }
            return icon
        }
    }
  }
</script>