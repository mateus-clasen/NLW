package com.clasenmateus.nlw.ui.component.market_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clasenmateus.nlw.data.model.Rule
import com.clasenmateus.nlw.ui.theme.Gray400
import com.clasenmateus.nlw.ui.theme.Gray500
import com.clasenmateus.nlw.ui.theme.Typography

/**
 * Created by Mateus H. Clasen on 12/12/2024.
 */

@Composable
fun NearbyMarketDetailsRules(modifier: Modifier = Modifier, rules: List<Rule>) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Regulamento", style = Typography.headlineSmall, color = Gray400)

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = rules.joinToString(separator = "/n", transform = { "* ${it.description}" }),
            style = Typography.labelMedium,
            color = Gray500,
            lineHeight = 24.sp
        )
    }
}