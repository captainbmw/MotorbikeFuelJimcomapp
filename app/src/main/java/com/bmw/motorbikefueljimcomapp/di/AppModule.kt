package com.bmw.motorbikefueljimcomapp.di



import android.app.Application
import androidx.room.Room
import com.bmw.motorbikefueljimcomapp.data.AppDatabase
import com.bmw.motorbikefueljimcomapp.data.MotorbikeDao
import com.bmw.motorbikefueljimcomapp.data.MotorbikeRegistrationViewModel
import com.bmw.motorbikefueljimcomapp.data.repository.MotorbikeREpository
import com.bmw.motorbikefueljimcomapp.data.repository.MotorbikeRoomRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "app_database" // Name of your database
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideMotorbikeDao(db: AppDatabase): MotorbikeDao {
        return db.motorbikeDao()
    }

    @Provides
    @Singleton
    fun provideMotorbikeRepository(motorbikeDao: MotorbikeDao): MotorbikeREpository {
        return MotorbikeRoomRepository(motorbikeDao)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideMotorbikeRegistrationViewModel(
        motorbikeRepository: MotorbikeREpository,
        auth: FirebaseAuth
    ): MotorbikeRegistrationViewModel {
        return MotorbikeRegistrationViewModel(motorbikeRepository, auth)
    }
}