async function getQuestion(qno){
    const response = await axios.get(`/questionMod/${qno}`)
    return response.data
}