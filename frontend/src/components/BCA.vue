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
            <span class="headline">또 사니?</span>
          </v-card-title>
          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>     
                <v-flex xs12 sm6>
                  <v-select v-model="editedItem.name"
                    :items="bcaType"
                    item-text="name"
                    item-value="value"
                    label="누가 짐?"
                    required
                  ></v-select>
                </v-flex>           
                <v-flex xs12>
                  <v-text-field v-model="editedItem.count" label="몇 잔?" hint="몇 잔?"></v-text-field>
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
    <template>
        <v-data-table
            :headers="headers"
            :items="items"
            class="elevation-1"
        >
            <template v-slot:items="props">
            <td>{{ props.item.workday }}</td>
            <td>{{ props.item.name }}</td>
            <td class="text-xs-right">{{ props.item.count }}</td>
            <td>{{ props.item.regDate }}</td>
            </template>
        </v-data-table>
    </template>
  </v-flex>
  </v-container>
  </div>
</template>
<script>
  export default {
    data: () => ({
      dialog: false,
      bcaType: [
          {name: "E", value: "E"},
          {name: "S", value: "S"},
          {name: "C", value: "C"}
      ],
      editedItem: {},
      defaultItem: {
          name: null,
          count: null
      },
      headers: [
        {
        text: 'Workday',
        align: 'left',
        value: 'workday'
        },
        {
        text: 'Name',
        align: 'left',
        sortable: false,
        value: 'name'
        },
        { text: 'Count',  align: 'center', value: 'count' },
        { text: 'reg date', align: 'center', value: 'regDate' }
      ],
      /*
      items: [
        {
          workday: '20190315',
          name: 'e',
          count: 4,
          regDate: ''
        }
      ]
      */
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
        this.getBcaList()
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
            const saveName = this.editedItem.name
            const saveCount = this.editedItem.count

            console.log("saveName : " + saveName +  " / saveCount : " + saveCount)

            this.axios
                .post(this.$API_URL+'/bca/save', {
                    headers: {
                        "Content-Type": "application/json"
                    },
                    name: saveName,
                    count: saveCount
                })
                .then(response => {
                    console.log(response.data)
                    this.editedItem = this.defaultItem
                    this.close()
                    this.getBcaList()
                })
        },
        getBcaList(){
            this.axios
                .get(this.$API_URL+'/bca')
                .then(response => {
                    console.log(response.data)
                    this.items = response.data
                })
        }
    }
  }
</script>