package net.corda.training.state

import net.corda.core.contracts.Amount
import net.corda.core.contracts.ContractState
import net.corda.core.contracts.LinearState
import net.corda.core.contracts.UniqueIdentifier
import net.corda.core.identity.AbstractParty
import net.corda.core.identity.Party
import net.corda.finance.DOLLARS
import net.corda.finance.POUNDS
import java.util.*

/**
 * This is where you'll add the definition of your state object. Look at the unit tests in [IOUStateTests] for
 * instructions on how to complete the [IOUState] class.
 *
 * Remove the "val data: String = "data" property before starting the [IOUState] tasks.
**/
data class IOUState(val amount: Amount<Currency>,
                    val lender: Party,
                    val borrower: Party,
                    val paid: Amount<Currency> = 0.DOLLARS,
                    override val linearId: UniqueIdentifier = UniqueIdentifier()): ContractState, LinearState {

    override val participants: List<Party> get() = listOf(lender, borrower)

    // Below function will execute states creation per every transaction undergone..

    fun pay(amttoPay : Amount<Currency> ) = copy(paid = paid.plus(amttoPay))

    fun withNewLender(newLender : Party) = copy(lender = newLender)

}
