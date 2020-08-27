import { mapGetters } from 'vuex'

export const carMixin = {
  computed: {
    ...mapGetters([
      'tableHeight'
    ])
  }
}
