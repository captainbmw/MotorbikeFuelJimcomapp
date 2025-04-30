package com.bmw.motorbikefueljimcomapp.ui.theme.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bmw.motorbikefueljimcomapp.data.DevotionViewModel
import com.bmw.motorbikefueljimcomapp.data.LoanViewModel
import com.bmw.motorbikefueljimcomapp.data.MotorbikeViewModel
import com.bmw.motorbikefueljimcomapp.data.entities.DevotionEntity
import com.bmw.motorbikefueljimcomapp.data.entities.LoanEntity
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HomeScreen(
    navController: NavHostController,
    loanViewModel: LoanViewModel = viewModel(),
    motorbikeViewModel: MotorbikeViewModel= viewModel(),
    devotionViewModel: DevotionViewModel = viewModel ()
) {
    val context = LocalContext.current
    val currentDate = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(2024)) }

    val activeLoans by loanViewModel.allLoans.collectAsState()
    val motorbikes by motorbikeViewModel.motorbikesByOwner.collectAsState()
    val todaysDevotion by produceState<DevotionEntity?>(initialValue = null){
        value = devotionViewModel.getDevotionByDate(currentDate)
    }

    Scaffold(
        topBar = { HomeAppbar()},
        bottomBar = { BottomAppBar {  } },
        floatingActionButton = {}
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            item {
                WelcomeCard(
                    onProfileClick = { navController.navigate("profile") }, // Replace with actual user name
                    userName = "Rider"
                )
            }

            // Stats Overview
            item {
                StatsOverviewSection(
                    activeLoans = activeLoans?.size() ?: 0,
                    totalPayable = activeLoans?.sumOf { it.totalPayable } ?: 0.0,
                    registeredBikes = motorbikes?.size() ?: 0
                )
            }

            // Today's Devotion
            item {
                TodaysDevotionCard(
                    devotion = todaysDevotion,
                    onSeeAllClick = { navController.navigate("devotions") }
                )
            }

            // Quick Actions
            item {
                QuickActionsSection(navController)
            }

            // Active Loans
            item {
                ActiveLoansSection(
                    loans = activeLoans,
                    onSeeAllClick = { navController.navigate("loans") }
                )
            }

        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeAppbar() {
    TopAppBar(
        title = { Text("JIMCOM Fuel Loans") },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications"
                )
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )

    )

}

@Composable
private fun WelcomeCard( onProfileClick: () -> Unit, userName: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Welcome Back,",
                    style= MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                Text(
                    text = userName,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ready for your next fuel loan?",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(onClick = onProfileClick) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }



        }

    }

}


@Composable
private fun StatsOverviewSection(activeLoans: Int, totalPayable: Double, registeredBikes: Int) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Your Overview",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatCard(
                value = activeLoans.toString(),
                label = "Active Loans",
                icon = Icons.Default.Refresh
            )
            StatCard(
                value = "KSh ${"%.2f".format(totalPayable)}",
                label = "Total Payable",
                icon = Icons.Default.Notifications
            )
            StatCard(
                value = registeredBikes.toString(),
                label = "Your Bikes",
                icon = Icons.Default.Send
            )
        }
    }
}

@Composable
private fun StatCard(value: String, label: String, icon: ImageVector) {
    Card(
        modifier = Modifier
            .width(110.dp)
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
private fun TodaysDevotionCard(devotion: DevotionEntity?, onSeeAllClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Today's Devotion",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = onSeeAllClick) {
                    Text("See All")
                }
            }

            if (devotion != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = devotion.title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = devotion.scripture,
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Text(
                    text = devotion.content.take(100) + "...",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            } else {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "No devotion for today",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Composable
private fun QuickActionsSection(navController: NavController) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Quick Actions",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            QuickActionButton(
                icon = Icons.Default.Lock,
                label = "Fuel Loan",
                onClick = { navController.navigate("apply_loan") }
            )
            QuickActionButton(
                icon = Icons.Default.Send,
                label = "Add Bike",
                onClick = { navController.navigate("register_bike") }
            )
            QuickActionButton(
                icon = Icons.Default.Share,
                label = "History",
                onClick = { navController.navigate("loan_history") }
            )
        }
    }
}

@Composable
private fun QuickActionButton(icon: ImageVector, label: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(110.dp)
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ActiveLoansSection(loans: List<LoanEntity>?, onSeeAllClick: () -> Unit) {
    val activeLoans = loans?.filter { it.status == "Active" }?.take(3) ?: emptyList()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Active Loans",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            TextButton(onClick = onSeeAllClick) {
                Text("See All")
            }
        }

        if (activeLoans.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(activeLoans) { loan ->
                    LoanItem(loan = loan)
                    Divider(modifier = Modifier.padding(vertical = 4.dp))
                }
            }
        } else {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "No active loans",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }
}

@Composable
private fun LoanItem(loan: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Loan",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Loan ${loan.loanNumber}",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "KSh ${"%.2f".format(loan.totalPayable)}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Due ${SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date(loan.dueDate))}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "View Details",
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
private fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == "home",
            onClick = { navController.navigate("home") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Loans") },
            label = { Text("Loans") },
            selected = currentRoute == "loans",
            onClick = { navController.navigate("loans") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.AccountBox, contentDescription = "Devotions") },
            label = { Text("Devotions") },
            selected = currentRoute == "devotions",
            onClick = { navController.navigate("devotions") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = currentRoute == "profile",
            onClick = { navController.navigate("profile") }
        )
    }
}

@Composable
private fun QuickLoanFAB(navController: NavController) {
    FloatingActionButton(
        onClick = { navController.navigate("apply_loan") },
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Icon(Icons.Default.Add, contentDescription = "Apply for Loan")
    }
}

@Preview
@Composable
private fun Homepage() {
    HomeScreen(rememberNavController())

}