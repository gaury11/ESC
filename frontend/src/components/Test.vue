<template>
  <v-container fluid grid-list-md>
    <v-data-iterator
      :items="items"
      :rows-per-page-items="rowsPerPageItems"
      :pagination.sync="pagination"
      content-tag="v-layout"
      hide-actions
      row
      wrap
    >
      <template v-slot:header>
        <v-toolbar
          class="mb-2"
          color="orange darken-1"
          dark
          flat
        >
          <v-toolbar-title>넌 이미 사고있어!</v-toolbar-title>
        </v-toolbar>
      </template>
      <template v-slot:item="props">
        <v-flex
          xs12
          sm6
          md4
          lg3
        >
          <v-card>
            <v-card-title class="subheading font-weight-bold">{{ props.item.workday }}</v-card-title>

            <v-divider></v-divider>

            <v-list dense>
              <v-list-tile>
                <v-list-tile-content>E:</v-list-tile-content>
                <v-list-tile-content class="align-end">{{ props.item.e }}</v-list-tile-content>
              </v-list-tile>

              <v-list-tile>
                <v-list-tile-content>S:</v-list-tile-content>
                <v-list-tile-content class="align-end">{{ props.item.s }}</v-list-tile-content>
              </v-list-tile>

              <v-list-tile>
                <v-list-tile-content>C:</v-list-tile-content>
                <v-list-tile-content class="align-end">{{ props.item.c }}</v-list-tile-content>
              </v-list-tile>              
            </v-list>
          </v-card>
        </v-flex>
      </template>
      <template v-slot:footer>
        <v-toolbar
          class="mt-2"
          color="orange darken-1"
          dark
          dense
          flat
        >
          <v-toolbar-title class="subheading">잘 먹을게</v-toolbar-title>
        </v-toolbar>
      </template>
    </v-data-iterator>
  </v-container>
</template>
<script>
  export default {
    data: () => ({
      rowsPerPageItems: [4, 8, 12],
      pagination: {
        rowsPerPage: 4
      },
      items: [
        {
          workday: '20190315',
          e: 3,
          s: 4,
          c: 1
        }
      ]
    })
  }
</script>