import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    tableHeight: 0
  },
  mutations: {
    'SET_TABLEHEIGHT': (state, tableHeight) => {
      state.tableHeight = tableHeight
    }
  },
  actions: {
    setTableHeight: ({ commit }, tableHeight) => {
      return commit('SET_TABLEHEIGHT', tableHeight)
    }
  },
  getters: {
    tableHeight: state => state.tableHeight
  }
})
