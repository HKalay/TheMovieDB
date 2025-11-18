package com.kalay.themoviedb.feature.main.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kalay.themoviedb.core.navigation.BottomNavigationBarItemType
import com.kalay.themoviedb.core.theme.Dark1Color
import com.kalay.themoviedb.core.theme.SecondaryColor
import com.kalay.themoviedb.core.theme.font.urbanistTypography

@Composable
fun BottomNavigationBar(
    selectedTab: BottomNavigationBarItemType,
    onTabSelected: (BottomNavigationBarItemType) -> Unit
) {
    NavigationBar(
        modifier = Modifier.clip(
            RoundedCornerShape(
                topStart = 12.dp,
                topEnd = 12.dp
            )
        ),
        containerColor = Dark1Color
    ) {
        BottomNavigationBarItemType.entries.forEach { tab ->
            NavigationBarItem(
                selected = selectedTab == tab,
                onClick = { onTabSelected(tab) },
                icon = {
                    Icon(
                        painterResource(id = tab.iconRes),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = tab.labelRes),
                        style = urbanistTypography().bodySmall.copy(
                            color = if (selectedTab == tab) SecondaryColor else Color.Gray
                        )
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = SecondaryColor,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = SecondaryColor,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Dark1Color
                )
            )
        }
    }
}
