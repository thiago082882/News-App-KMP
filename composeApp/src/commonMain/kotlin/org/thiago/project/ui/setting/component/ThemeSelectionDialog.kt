package org.thiago.project.ui.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kmp_news_app.composeapp.generated.resources.Res
import kmp_news_app.composeapp.generated.resources.apply
import kmp_news_app.composeapp.generated.resources.cancel
import kmp_news_app.composeapp.generated.resources.choose_a_theme
import kmp_news_app.composeapp.generated.resources.dark_mode
import kmp_news_app.composeapp.generated.resources.light_mode
import org.jetbrains.compose.resources.stringResource
import org.thiago.project.theme.mediumPadding
import org.thiago.project.theme.xLargePadding
import org.thiago.project.theme.xSmallPadding
import org.thiago.project.utils.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeSelectionDialog(
    onThemeChange: (Theme) -> Unit, onDismissRequest: () -> Unit, currentTheme: Theme
) {

    var currentSelectedTheme by remember { mutableStateOf(currentTheme) }

    BasicAlertDialog(onDismissRequest = onDismissRequest, content = {
        Surface(
            modifier = Modifier.wrapContentWidth().wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(mediumPadding)) {

                Text(
                    text = stringResource(Res.string.choose_a_theme),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(xSmallPadding)
                )

                Row(
                    modifier = Modifier.fillMaxWidth().clickable {
                        currentSelectedTheme = Theme.Light
                    },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = currentSelectedTheme == Theme.Light,
                        onClick = { currentSelectedTheme = Theme.Light },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(text = stringResource(Res.string.light_mode))
                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .clickable { currentSelectedTheme = Theme.Dark },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = currentSelectedTheme == Theme.Dark,
                        onClick = { currentSelectedTheme = Theme.Dark },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(text = stringResource(Res.string.dark_mode))
                }

                Spacer(modifier = Modifier.height(xLargePadding))

                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text(text = stringResource(Res.string.cancel))
                    }
                    Spacer(modifier = Modifier.width(mediumPadding))
                    TextButton(onClick = { onThemeChange(currentSelectedTheme) }) {
                        Text(text = stringResource(Res.string.apply))
                    }
                }
            }
        }
    })
}

