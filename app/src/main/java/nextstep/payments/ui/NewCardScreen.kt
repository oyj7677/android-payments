package nextstep.payments.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.NewCardViewModel
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme

// Stateful
@Composable
fun NewCardScreen(
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
) {
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()

    NewCardScreen(
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDatedNumber = viewModel::setExpiredDate,
        setOwnerNamedNumber = viewModel::setOwnerName,
        setPasswordNumber = viewModel::setPassword,
        modifier = modifier
    )
}

// Stateless
@Composable
private fun NewCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    setCardNumber: (String) -> Unit,
    setExpiredDatedNumber: (String) -> Unit,
    setOwnerNamedNumber: (String) -> Unit,
    setPasswordNumber: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { NewCardTopBar(onBackClick = { TODO() }, onSaveClick = { TODO() }) },
        modifier = modifier
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            PaymentCard()

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = cardNumber,
                onValueChange = { setCardNumber(it) },
                label = { stringResource(id = R.string.text_card_number) },
                placeholder = { stringResource(id = R.string.placeholder_card_number) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = expiredDate,
                onValueChange = { setExpiredDatedNumber(it) },
                label = { stringResource(id = R.string.text_card_expiration_date) },
                placeholder = { stringResource(id = R.string.placeholder_card_expiration_date) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = ownerName,
                onValueChange = { setOwnerNamedNumber(it) },
                label = { stringResource(id = R.string.text_card_owner_name) },
                placeholder = { stringResource(id = R.string.placeholder_card_owner_name) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = password,
                onValueChange = { setPasswordNumber(it) },
                label = { stringResource(id = R.string.text_password) },
                placeholder = { stringResource(id = R.string.placeholder_password) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }
}

@Preview
@Composable
private fun NewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            viewModel = NewCardViewModel().apply {
                setCardNumber("0000 - 0000 - 0000 -0000")
                setExpiredDate("02/26")
                setOwnerName("김수현")
                setPassword("1234")
            }
        )
    }
}

@Preview
@Composable
private fun StatelessNewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            cardNumber = "0000 - 0000 - 0000 -0000",
            expiredDate = "02/26",
            ownerName = "김수현",
            password = "1234",
            setCardNumber = {},
            setExpiredDatedNumber = {},
            setOwnerNamedNumber = {},
            setPasswordNumber = {}
        )
    }
}
