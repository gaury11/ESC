<template>
<div id="app">
  <v-app id="inspire">
    <v-navigation-drawer
      clipped
      fixed
      v-model="drawer"
      app
    >
      <v-list dense>
        <v-list-tile to="/">
          <v-list-tile-action>
            <v-icon>local_atm</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>넌 이미 사고있어!</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile to="/msg-api">
          <v-list-tile-action>
            <v-icon>send</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>Mag-Api 테스트</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile to="/timeline">
          <v-list-tile-action>
            <v-icon>timeline</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>타임 라인</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile to="/test">
          <v-list-tile-action>
            <v-icon>code</v-icon> 
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>Test</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>

    <v-toolbar app fixed clipped-left color="orange darken-1" dark>
      <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
      <v-toolbar-title ><h1>E.S.C</h1></v-toolbar-title>
    </v-toolbar>

    <v-content>
      <router-view></router-view>
    </v-content>

    <v-footer app fixed>
      <span> &copy; 2019</span>
    </v-footer>
  </v-app>
</div>
</template>

<script>
//import HelloWorld from './components/HelloWorld'

export default {
  name: 'App',
  data () {
    return {
      //
    }
  }
}
</script>
