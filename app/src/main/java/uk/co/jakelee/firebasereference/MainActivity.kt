package uk.co.jakelee.firebasereference

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import uk.co.jakelee.firebasereference.analytics.audiences.AudiencesFragment
import uk.co.jakelee.firebasereference.analytics.conversions.ConversionsFragment
import uk.co.jakelee.firebasereference.analytics.dashboard.DashboardFragment
import uk.co.jakelee.firebasereference.analytics.debugview.DebugViewFragment
import uk.co.jakelee.firebasereference.analytics.events.EventsFragment
import uk.co.jakelee.firebasereference.analytics.funnels.FunnelsFragment
import uk.co.jakelee.firebasereference.analytics.latest_release.LatestReleaseFragment
import uk.co.jakelee.firebasereference.analytics.retention.RetentionFragment
import uk.co.jakelee.firebasereference.analytics.streamview.StreamViewFragment
import uk.co.jakelee.firebasereference.analytics.user_properties.UserPropertiesFragment
import uk.co.jakelee.firebasereference.develop.authentication.AuthenticationFragment
import uk.co.jakelee.firebasereference.develop.database.DatabaseFragment
import uk.co.jakelee.firebasereference.develop.functions.FunctionsFragment
import uk.co.jakelee.firebasereference.develop.hosting.HostingFragment
import uk.co.jakelee.firebasereference.develop.ml_kit.MLKitFragment
import uk.co.jakelee.firebasereference.develop.storage.StorageFragment
import uk.co.jakelee.firebasereference.grow.ab_testing.ABTestingFragment
import uk.co.jakelee.firebasereference.grow.admob.AdmobFragment
import uk.co.jakelee.firebasereference.grow.cloud_messaging.CloudMessagingFragment
import uk.co.jakelee.firebasereference.grow.dynamic_links.DynamicLinksFragment
import uk.co.jakelee.firebasereference.grow.in_app_messaging.InAppMessagingFragment
import uk.co.jakelee.firebasereference.grow.predictions.PredictionsFragment
import uk.co.jakelee.firebasereference.grow.remote_config.RemoteConfigFragment
import uk.co.jakelee.firebasereference.quality.crashlytics.CrashlyticsFragment
import uk.co.jakelee.firebasereference.quality.performance.PerformanceFragment
import uk.co.jakelee.firebasereference.quality.test_lab.TestLabFragment


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_authentication -> { displayFragment(AuthenticationFragment()) }
            R.id.nav_database -> { displayFragment(DatabaseFragment()) }
            R.id.nav_functions -> { displayFragment(FunctionsFragment()) }
            R.id.nav_hosting -> { displayFragment(HostingFragment()) }
            R.id.nav_ml_kit -> { displayFragment(MLKitFragment()) }
            R.id.nav_storage -> { displayFragment(StorageFragment()) }
            R.id.nav_crashlytics -> { displayFragment(CrashlyticsFragment()) }
            R.id.nav_performance -> { displayFragment(PerformanceFragment()) }
            R.id.nav_test_lab -> { displayFragment(TestLabFragment()) }
            R.id.nav_audiences -> { displayFragment(AudiencesFragment()) }
            R.id.nav_conversions -> { displayFragment(ConversionsFragment()) }
            R.id.nav_dashboard -> { displayFragment(DashboardFragment()) }
            R.id.nav_debugview -> { displayFragment(DebugViewFragment()) }
            R.id.nav_events -> { displayFragment(EventsFragment()) }
            R.id.nav_funnels -> { displayFragment(FunnelsFragment()) }
            R.id.nav_latest_release -> { displayFragment(LatestReleaseFragment()) }
            R.id.nav_retention -> { displayFragment(RetentionFragment()) }
            R.id.nav_streamview -> { displayFragment(StreamViewFragment()) }
            R.id.nav_user_properties -> { displayFragment(UserPropertiesFragment()) }
            R.id.nav_ab_testing -> { displayFragment(ABTestingFragment()) }
            R.id.nav_admob -> { displayFragment(AdmobFragment()) }
            R.id.nav_cloud_messaging -> { displayFragment(CloudMessagingFragment()) }
            R.id.nav_dynamic_links -> { displayFragment(DynamicLinksFragment()) }
            R.id.nav_in_app_messaging -> { displayFragment(InAppMessagingFragment()) }
            R.id.nav_predictions -> { displayFragment(PredictionsFragment()) }
            R.id.nav_remote_config -> { displayFragment(RemoteConfigFragment()) }
            R.id.nav_tutorial -> {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://blog.jakelee.co.uk/tag/firebase")))
            }
            R.id.nav_about -> { displayFragment(AboutFragment()) }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun displayFragment(fragment: Fragment) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.mainFrame, fragment).commit()
    }
}
