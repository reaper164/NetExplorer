package com.reaper.data.repository

import android.util.Log
import com.reaper.data.local.RouterDao
import com.reaper.data.local.entity.RouterEntity
import com.reaper.domain.Resource
import com.reaper.domain.model.Range
import com.reaper.domain.model.RouterInfo
import com.reaper.domain.model.RouterscanParams
import com.reaper.domain.repository.RouterScanRepository
import com.reaper.data.mappers.toRouterInfo
import com.reaper.data.util.Utils
import inet.ipaddr.IPAddress
import inet.ipaddr.IPAddressString
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class RouterScanRepositoryImpl(
    private val dao: RouterDao
) : RouterScanRepository {

    private val TAG = this.javaClass.simpleName

    override fun scanRouters(routerscanParams: RouterscanParams): Flow<Resource<List<RouterInfo>>> =
        flow {
            emit(Resource.Loading())

            val oldRouters = dao.getAllRouters().map { it.toRouterInfo() }
            emit(Resource.Loading(data = oldRouters))

            try {
                val ipAddressesToScan = mutableListOf<IPAddress>()
                routerscanParams.listIp?.map { IPAddressString(it).toAddress() }
                    ?.let { ipAddressesToScan.addAll(it) }
                routerscanParams.rangeIp?.map { pair: Range ->
                    IPAddressString(pair.first).toAddress()
                        .toSequentialRange(IPAddressString(pair.second).toAddress())
                }?.forEach { range ->
                    for (address in range.iterable) {
                        ipAddressesToScan.add(address)
                    }
                }

                var alreadyScanned = 0
                val routers = mutableListOf<RouterEntity>()

                for (ipAddress in ipAddressesToScan) {
                    // TODO determine ip availability (ping)
                    Log.e(TAG, "Scan $ipAddress")
                    if (Utils.pingHost(ipAddress.toString(), 80, 5000)) {

                        // TODO scan ip with routerscan
                        routers.add(
                            RouterEntity(
                                ip = ipAddress.toString(),
                                status = "Success",
                                title = "Available",
                                port = "8080"
                            )
                        )
                    }

                    alreadyScanned++
                    val percentage = alreadyScanned * 100 / ipAddressesToScan.size
                    emit(Resource.Progress(progress = percentage))
                }

                dao.insertRouter(routers)

            } catch (e: IOException) {
                emit(Resource.Error(message = "Error while scan routers"))
            }

            val newRouters = dao.getAllRouters().map { it.toRouterInfo() }
            emit(Resource.Success(data = newRouters))

        }
}