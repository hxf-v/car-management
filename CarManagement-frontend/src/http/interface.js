import axios from './api'

export const getSoftware = () => {
  return axios({
    url: '/hpc/software'
  })
}

export default {
  getSoftware
}
