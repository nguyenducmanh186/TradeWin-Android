package trade.win.invitation

import trade.win.history.HistoryResponse

interface IInvitation {
    fun onSuccess(ivitation: InvitationResponse)
    fun onError()
    fun onError(msg: String)
    fun onErrorFailure(t: Throwable)
    fun onExpireToken()
}